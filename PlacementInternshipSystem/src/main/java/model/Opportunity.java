package model;

public class Opportunity {

    private int opportunityId;
    private int companyId;
    private String title;
    private String type;
    private String description;
    private String requiredSkill;
    private double minimumCgpa;
    private String location;
    private double salary;

    public Opportunity() {
    }

    public Opportunity(int companyId, String title, String type,
                       String description, String requiredSkill,
                       double minimumCgpa, String location, double salary) {

        this.companyId = companyId;
        this.title = title;
        this.type = type;
        this.description = description;
        this.requiredSkill = requiredSkill;
        this.minimumCgpa = minimumCgpa;
        this.location = location;
        this.salary = salary;
    }
    public int getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(int opportunityId) {
        this.opportunityId = opportunityId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public double getMinimumCgpa() {
        return minimumCgpa;
    }

    public void setMinimumCgpa(double minimumCgpa) {
        this.minimumCgpa = minimumCgpa;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "opportunityId=" + opportunityId +
                ", companyId=" + companyId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", requiredSkill='" + requiredSkill + '\'' +
                ", minimumCgpa=" + minimumCgpa +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                '}';
    }
}