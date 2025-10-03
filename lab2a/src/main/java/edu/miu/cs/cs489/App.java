package edu.miu.cs.cs489;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        List<Employee> employees = SampleData.employees();

        // Always resolve to <module-root>/output
        Path outDir = moduleRoot().resolve("output");
        Files.createDirectories(outDir);

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
                Files.writeString(outDir.resolve("all-employees.json"), json);
                System.out.println(json);
                break;
            }
            case "upcoming": {
                LocalDate today = LocalDate.now();
                var list = Reports.quarterlyUpcomingEnrollees(employees, today);
                String json = JsonUtil.toJson(list);
                Files.writeString(outDir.resolve("quarterly-upcoming-enrollees.json"), json);
                System.out.println(json);

                var q = Reports.nextQuarter(today);
                System.err.println("Next quarter window: " + q.start + " to " + q.end);
                break;
            }
            default:
                System.out.println("Unknown command.");
        }
    }

    // Resolves the module root whether running from classes/ or a shaded jar
    private static Path moduleRoot() {
        try {
            var uri = App.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            Path loc = Path.of(uri);                                // .../lab2a/target/classes OR .../lab2a/target/lab2a-*.jar
            Path targetDir = loc.toString().endsWith(".jar") ? loc.getParent() : loc;
            Path moduleRoot = targetDir.getParent();                // .../lab2a
            return (moduleRoot != null) ? moduleRoot : Path.of(".").toAbsolutePath();
        } catch (Exception e) {
            return Path.of(".").toAbsolutePath();
        }
    }
}
