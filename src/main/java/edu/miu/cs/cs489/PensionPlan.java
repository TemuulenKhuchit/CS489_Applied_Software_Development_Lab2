package edu.miu.cs.cs489;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PensionPlan {
    private String planReferenceNumber;
    private LocalDate enrollmentDate;       // can be null (not yet enrolled)
    private BigDecimal monthlyContribution; // can be null

    public PensionPlan(String planReferenceNumber, LocalDate enrollmentDate, BigDecimal monthlyContribution) {
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
    }

    public String getPlanReferenceNumber() { return planReferenceNumber; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public BigDecimal getMonthlyContribution() { return monthlyContribution; }
}
