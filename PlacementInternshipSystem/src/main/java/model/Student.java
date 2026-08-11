package model;

public class Student {

    private int studentId;
    private String name;
    private String email;
    private String phone;
    private String degree;
    private String branch;
    private double cgpa;
    private int graduationYear;

    public Student() {
    }

    public Student(String name, String email, String phone,
                   String degree, String branch,
                   double cgpa, int graduationYear) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.degree = degree;
        this.branch = branch;
        this.cgpa = cgpa;
        this.graduationYear = graduationYear;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", degree='" + degree + '\'' +
                ", branch='" + branch + '\'' +
                ", cgpa=" + cgpa +
                ", graduationYear=" + graduationYear +
                '}';
    }
}