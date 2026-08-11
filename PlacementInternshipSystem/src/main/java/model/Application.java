package model;

import java.time.LocalDate;

public class Application {

    private int applicationId;
    private int studentId;
    private int opportunityId;
    private LocalDate applicationDate;
    private String status;

    public Application() {
    }

    public Application(int studentId, int opportunityId,
                       LocalDate applicationDate, String status) {

        this.studentId = studentId;
        this.opportunityId = opportunityId;
        this.applicationDate = applicationDate;
        this.status = status;
    }
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(int opportunityId) {
        this.opportunityId = opportunityId;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", studentId=" + studentId +
                ", opportunityId=" + opportunityId +
                ", applicationDate=" + applicationDate +
                ", status='" + status + '\'' +
                '}';
    }
}