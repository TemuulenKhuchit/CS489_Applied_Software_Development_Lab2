package edu.miu.cs.cs489;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Reports {

    // 1) All employees: sort salary desc, then lastName asc
    public static List<Employee> allEmployeesSorted(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator
                        .comparing(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());
    }

    // 2) Quarterly upcoming enrollees:
    // "NOT enrolled" and "employment >= 3 years on any date between first and last day of next quarter"
    public static List<Employee> quarterlyUpcomingEnrollees(List<Employee> employees, LocalDate today) {
        QuarterRange nextQ = nextQuarter(today);

        return employees.stream()
                .filter(e -> !e.isEnrolled())
                .filter(e -> qualifiesInWindow(e.getEmploymentDate(), nextQ.start, nextQ.end))
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());
    }

    private static boolean qualifiesInWindow(LocalDate employmentDate, LocalDate windowStart, LocalDate windowEnd) {
        // Check if there exists a date d in [windowStart, windowEnd] such that d - employmentDate >= 3 years.
        // Equivalent: employmentDate <= (windowEnd - 3 years)
        LocalDate threeYearsCutoff = windowEnd.minusYears(3);
        return !employmentDate.isAfter(threeYearsCutoff);
    }

    public static QuarterRange nextQuarter(LocalDate today) {
        int month = today.getMonthValue();
        int year = today.getYear();

        int nextQStartMonth;
        if (month <= 3)       nextQStartMonth = 4;   // Q2
        else if (month <= 6)  nextQStartMonth = 7;   // Q3
        else if (month <= 9)  nextQStartMonth = 10;  // Q4
        else { nextQStartMonth = 1; year += 1; }     // Q1 next year

        Month startM = Month.of(nextQStartMonth);
        LocalDate start = LocalDate.of(year, startM, 1);
        LocalDate end = start.plusMonths(3).minusDays(1); // last day of the quarter
        return new QuarterRange(start, end);
    }

    public static class QuarterRange {
        public final LocalDate start;
        public final LocalDate end;
        public QuarterRange(LocalDate start, LocalDate end) { this.start = start; this.end = end; }
    }
}
