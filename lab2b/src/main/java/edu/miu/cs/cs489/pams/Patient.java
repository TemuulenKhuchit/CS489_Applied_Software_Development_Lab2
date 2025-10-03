package edu.miu.cs.cs489.pams;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.Period;

public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private String phone;          // may be null
    private String email;          // may be null
    private String mailingAddress; // may be null
    private LocalDate dateOfBirth;

    public Patient(int patientId, String firstName, String lastName,
                   String phone, String email, String mailingAddress,
                   LocalDate dateOfBirth) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.mailingAddress = mailingAddress;
        this.dateOfBirth = dateOfBirth;
    }

    public int getPatientId() { return patientId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getMailingAddress() { return mailingAddress; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }

    // Include computed age in JSON output as required by the lab
    @JsonProperty("age")
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
