package edu.miu.cs.cs489.pams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class PAMSApp {
    public static void main(String[] args) throws Exception {
        // Build data from the handout (nulls where values are missing)
        List<Patient> patients = List.of(
            new Patient(1, "Daniel", "Agar", "(641) 123-0009", "dagar@m.as", "1 N Street",
                        LocalDate.of(1987, 1, 19)),
            new Patient(2, "Ana", "Smith", null, "amsith@te.edu", null,
                        LocalDate.of(1948, 12, 5)),
            new Patient(3, "Marcus", "Garvey", "(123) 292-0018", null, "4 East Ave",
                        LocalDate.of(2001, 9, 18)),
            new Patient(4, "Jeff", "Goldbloom", "(999) 165-1192", "jgold@es.co.za", null,
                        LocalDate.of(1995, 2, 28)),
            new Patient(5, "Mary", "Washington", null, null, "30 W Burlington",
                        LocalDate.of(1932, 5, 31))
        );

        // Sort by age DESC (oldest first)
        var sorted = patients.stream()
                .sorted(Comparator.comparingInt(Patient::getAge).reversed())
                .toList();

        // JSON mapper
        var mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Always write under <module-root>/output
        Path outDir = moduleRoot().resolve("output");
        Files.createDirectories(outDir);
        Path out = outDir.resolve("patients-by-age.json");
        mapper.writeValue(out.toFile(), sorted);

        System.out.println("Wrote JSON to " + out.toAbsolutePath());
    }

    // Resolve the module root whether running from classes/ or a shaded JAR
    private static Path moduleRoot() {
        try {
            var uri = PAMSApp.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            Path loc = Path.of(uri);                              // .../lab2b/target/classes OR .../lab2b/target/lab2b-*.jar
            Path targetDir = loc.toString().endsWith(".jar") ? loc.getParent() : loc;
            Path moduleRoot = targetDir.getParent();              // .../lab2b
            return (moduleRoot != null) ? moduleRoot : Path.of(".").toAbsolutePath();
        } catch (Exception e) {
            return Path.of(".").toAbsolutePath();
        }
    }
}
