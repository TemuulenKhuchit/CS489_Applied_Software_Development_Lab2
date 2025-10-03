package edu.miu.cs.cs489;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static List<Employee> employees() {
        List<Employee> list = new ArrayList<>();

        // Row 1: EX1089, Daniel Agar, 105,945.50, 2023-01-17, enrollmentDate=null, $100.00
        list.add(new Employee(
                1L, "Daniel", "Agar",
                LocalDate.parse("2023-01-17"),
                new BigDecimal("105945.50"),
                new PensionPlan("EX1089", null, new BigDecimal("100.00"))
        ));

        // Row 2: Benard Shaw, 197,750.00, 2022-09-03, (no planRef), enrollmentDate=2025-09-03, monthlyContribution=null
        // Interpret as "not enrolled" because no Plan reference; keep pensionPlan null.
        list.add(new Employee(
                2L, "Benard", "Shaw",
                LocalDate.parse("2022-09-03"),
                new BigDecimal("197750.00"),
                null
        ));

        // Row 3: SM2307, Carly Agar, 842,000.75, 2014-05-16, 2017-05-17, $1555.50
        list.add(new Employee(
                3L, "Carly", "Agar",
                LocalDate.parse("2014-05-16"),
                new BigDecimal("842000.75"),
                new PensionPlan("SM2307", LocalDate.parse("2017-05-17"), new BigDecimal("1555.50"))
        ));

        // Row 4: Wesley Schneider, 74,500.00, 2023-07-21 (no plan)
        list.add(new Employee(
                4L, "Wesley", "Schneider",
                LocalDate.parse("2023-07-21"),
                new BigDecimal("74500.00"),
                null
        ));

        // Row 5: Anna Wiltord, 85,750.00, 2023-03-15 (no plan)
        list.add(new Employee(
                5L, "Anna", "Wiltord",
                LocalDate.parse("2023-03-15"),
                new BigDecimal("85750.00"),
                null
        ));

        // Row 6: Yosef Tesfalem, 100,000.00, 2024-10-31 (no plan)
        list.add(new Employee(
                6L, "Yosef", "Tesfalem",
                LocalDate.parse("2024-10-31"),
                new BigDecimal("100000.00"),
                null
        ));

        return list;
    }
}
