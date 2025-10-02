package edu.miu.cs.cs489;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Employee> employees = SampleData.employees();

        // Create output directory for JSON + screenshots storage reference (optional)
        Files.createDirectories(Path.of("output"));

        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("  java -jar app.jar print-all");
            System.out.println("  java -jar app.jar upcoming");
            return;
        }

        switch (args[0]) {
            case "print-all": {
                var sorted = Reports.allEmployeesSorted(employees);
                String json = JsonUtil.toJson(sorted);
                Files.writeString(Path.of("output", "all-employees.json"), json);
                System.out.println(json);
                break;
            }
            case "upcoming": {
                LocalDate today = LocalDate.now();
                var list = Reports.quarterlyUpcomingEnrollees(employees, today);
                String json = JsonUtil.toJson(list);
                Files.writeString(Path.of("output", "quarterly-upcoming-enrollees.json"), json);
                System.out.println(json);

                var q = Reports.nextQuarter(today);
                System.err.println("Next quarter window: " + q.start + " to " + q.end);
                break;
            }
            default:
                System.out.println("Unknown command.");
        }
    }
}
