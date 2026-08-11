package model;

public class Company {

    private int companyId;
    private String companyName;
    private String email;
    private String location;
    private String website;
    public Company() {
    }

    public Company(String companyName, String email,
                   String location, String website) {

        this.companyName = companyName;
        this.email = email;
        this.location = location;
        this.website = website;
    }
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}