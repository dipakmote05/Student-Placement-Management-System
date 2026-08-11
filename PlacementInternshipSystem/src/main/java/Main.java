import model.Application;
import model.Company;
import model.Opportunity;
import model.Student;

import service.ApplicationService;
import service.CompanyService;
import service.OpportunityService;
import service.StudentService;
import service.EligibilityService;

import util.InputValidator;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        CompanyService companyService = new CompanyService();
        OpportunityService opportunityService = new OpportunityService();
        ApplicationService applicationService = new ApplicationService();
        EligibilityService eligibilityService =
                new EligibilityService();

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       PLACEMENT & INTERNSHIP SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Student Management");
            System.out.println("2. Company Management");
            System.out.println("3. Opportunity Management");
            System.out.println("4. Application Management");
            System.out.println("5. Exit");
            System.out.println("========================================");

            int choice = InputValidator.readInt("Enter your choice: ");

            switch (choice) {

                // =====================================================
                // STUDENT MANAGEMENT
                // =====================================================

                case 1:

                    while (true) {

                        System.out.println();
                        System.out.println("1) Add Student");
                        System.out.println("2) View All Students");
                        System.out.println("3) Find Student By ID");
                        System.out.println("4) Update Student");
                        System.out.println("5) Delete Student");
                        System.out.println("6) Back");
                        System.out.println("========================================");

                        int studentChoice =
                                InputValidator.readInt("Enter your choice: ");

                        switch (studentChoice) {

                            // ADD STUDENT
                            case 1:

                                System.out.println();
                                System.out.println("---------- ADD STUDENT ----------");

                                String name =
                                        InputValidator.readNonEmptyString(
                                                "Enter name: ");

                                String email =
                                        InputValidator.readNonEmptyString(
                                                "Enter email: ");

                                String phone =
                                        InputValidator.readNonEmptyString(
                                                "Enter phone: ");

                                String degree =
                                        InputValidator.readNonEmptyString(
                                                "Enter degree: ");

                                String branch =
                                        InputValidator.readNonEmptyString(
                                                "Enter branch: ");

                                double cgpa =
                                        InputValidator.readDouble(
                                                "Enter CGPA: ");

                                int graduationYear =
                                        InputValidator.readInt(
                                                "Enter graduation year: ");

                                Student student = new Student(
                                        name,
                                        email,
                                        phone,
                                        degree,
                                        branch,
                                        cgpa,
                                        graduationYear
                                );

                                studentService.addStudent(student);

                                break;


                            // VIEW ALL STUDENTS
                            case 2:

                                System.out.println();
                                System.out.println("---------- ALL STUDENTS ----------");

                                List<Student> students =
                                        studentService.getAllStudents();

                                if (students.isEmpty()) {

                                    System.out.println("No students found.");

                                } else {

                                    for (Student s : students) {
                                        System.out.println(s);
                                    }
                                }

                                break;


                            // FIND STUDENT BY ID
                            case 3:

                                System.out.println();

                                int studentId =
                                        InputValidator.readInt(
                                                "Enter student ID: ");

                                Student foundStudent =
                                        studentService.getStudentById(studentId);

                                if (foundStudent != null) {

                                    System.out.println(foundStudent);

                                } else {

                                    System.out.println("Student not found.");
                                }

                                break;


                            // UPDATE STUDENT
                            case 4:

                                System.out.println();

                                int updateStudentId =
                                        InputValidator.readInt(
                                                "Enter student ID: ");

                                Student existingStudent =
                                        studentService.getStudentById(
                                                updateStudentId);

                                if (existingStudent == null) {

                                    System.out.println("Student not found.");
                                    break;
                                }

                                System.out.println("Enter new details:");

                                String newName =
                                        InputValidator.readNonEmptyString(
                                                "Enter name: ");

                                String newEmail =
                                        InputValidator.readNonEmptyString(
                                                "Enter email: ");

                                String newPhone =
                                        InputValidator.readNonEmptyString(
                                                "Enter phone: ");

                                String newDegree =
                                        InputValidator.readNonEmptyString(
                                                "Enter degree: ");

                                String newBranch =
                                        InputValidator.readNonEmptyString(
                                                "Enter branch: ");

                                double newCgpa =
                                        InputValidator.readDouble(
                                                "Enter CGPA: ");

                                int newGraduationYear =
                                        InputValidator.readInt(
                                                "Enter graduation year: ");

                                existingStudent.setStudentId(
                                        updateStudentId);

                                existingStudent.setName(newName);
                                existingStudent.setEmail(newEmail);
                                existingStudent.setPhone(newPhone);
                                existingStudent.setDegree(newDegree);
                                existingStudent.setBranch(newBranch);
                                existingStudent.setCgpa(newCgpa);
                                existingStudent.setGraduationYear(
                                        newGraduationYear);

                                studentService.updateStudent(
                                        existingStudent);

                                break;


                            // DELETE STUDENT
                            case 5:

                                System.out.println();

                                int deleteStudentId =
                                        InputValidator.readInt(
                                                "Enter student ID: ");

                                studentService.deleteStudent(
                                        deleteStudentId);

                                break;


                            // BACK
                            case 6:
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (studentChoice == 6) {
                            break;
                        }
                    }

                    break;


                // =====================================================
                // COMPANY MANAGEMENT
                // =====================================================

                case 2:

                    while (true) {

                        System.out.println();
                        System.out.println("1) Add Company");
                        System.out.println("2) View All Companies");
                        System.out.println("3) Find Company By ID");
                        System.out.println("4) Update Company");
                        System.out.println("5) Delete Company");
                        System.out.println("6) Back");
                        System.out.println("========================================");

                        int companyChoice =
                                InputValidator.readInt(
                                        "Enter your choice: ");

                        switch (companyChoice) {

                            // ADD COMPANY
                            case 1:

                                System.out.println();
                                System.out.println("---------- ADD COMPANY ----------");

                                String companyName =
                                        InputValidator.readNonEmptyString(
                                                "Enter company name: ");

                                String companyEmail =
                                        InputValidator.readNonEmptyString(
                                                "Enter email: ");

                                String location =
                                        InputValidator.readNonEmptyString(
                                                "Enter location: ");

                                String website =
                                        InputValidator.readNonEmptyString(
                                                "Enter website: ");

                                Company company = new Company(
                                        companyName,
                                        companyEmail,
                                        location,
                                        website
                                );

                                companyService.addCompany(company);

                                break;


                            // VIEW ALL COMPANIES
                            case 2:

                                System.out.println();
                                System.out.println(
                                        "---------- ALL COMPANIES ----------");

                                List<Company> companies =
                                        companyService.getAllCompanies();

                                if (companies.isEmpty()) {

                                    System.out.println(
                                            "No companies found.");

                                } else {

                                    for (Company c : companies) {
                                        System.out.println(c);
                                    }
                                }

                                break;


                            // FIND COMPANY BY ID
                            case 3:

                                System.out.println();

                                int companyId =
                                        InputValidator.readInt(
                                                "Enter company ID: ");

                                Company foundCompany =
                                        companyService.getCompanyById(
                                                companyId);

                                if (foundCompany != null) {

                                    System.out.println(foundCompany);

                                } else {

                                    System.out.println(
                                            "Company not found.");
                                }

                                break;


                            // UPDATE COMPANY
                            case 4:

                                System.out.println();

                                int updateCompanyId =
                                        InputValidator.readInt(
                                                "Enter company ID: ");

                                Company existingCompany =
                                        companyService.getCompanyById(
                                                updateCompanyId);

                                if (existingCompany == null) {

                                    System.out.println(
                                            "Company not found.");

                                    break;
                                }

                                System.out.println("Enter new details:");

                                String newCompanyName =
                                        InputValidator.readNonEmptyString(
                                                "Enter company name: ");

                                String newCompanyEmail =
                                        InputValidator.readNonEmptyString(
                                                "Enter email: ");

                                String newLocation =
                                        InputValidator.readNonEmptyString(
                                                "Enter location: ");

                                String newWebsite =
                                        InputValidator.readNonEmptyString(
                                                "Enter website: ");

                                existingCompany.setCompanyId(
                                        updateCompanyId);

                                existingCompany.setCompanyName(
                                        newCompanyName);

                                existingCompany.setEmail(
                                        newCompanyEmail);

                                existingCompany.setLocation(
                                        newLocation);

                                existingCompany.setWebsite(
                                        newWebsite);

                                companyService.updateCompany(
                                        existingCompany);

                                break;


                            // DELETE COMPANY
                            case 5:

                                System.out.println();

                                int deleteCompanyId =
                                        InputValidator.readInt(
                                                "Enter company ID: ");

                                companyService.deleteCompany(
                                        deleteCompanyId);

                                break;


                            // BACK
                            case 6:
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (companyChoice == 6) {
                            break;
                        }
                    }

                    break;


                // =====================================================
                // OPPORTUNITY MANAGEMENT
                // =====================================================

                case 3:

                    while (true) {

                        System.out.println();
                        System.out.println("1) Add Opportunity");
                        System.out.println("2) View All Opportunities");
                        System.out.println("3) Find Opportunity By ID");
                        System.out.println("4) Update Opportunity");
                        System.out.println("5) Delete Opportunity");
                        System.out.println("6) Back");
                        System.out.println("========================================");

                        int opportunityChoice =
                                InputValidator.readInt(
                                        "Enter your choice: ");

                        switch (opportunityChoice) {

                            // ADD OPPORTUNITY
                            case 1:

                                System.out.println();
                                System.out.println(
                                        "---------- ADD OPPORTUNITY ----------");

                                int companyId =
                                        InputValidator.readInt(
                                                "Enter company ID: ");

                                String title =
                                        InputValidator.readNonEmptyString(
                                                "Enter title: ");

                                String type =
                                        InputValidator.readNonEmptyString(
                                                "Enter type: ");

                                String description =
                                        InputValidator.readNonEmptyString(
                                                "Enter description: ");

                                String requiredSkill =
                                        InputValidator.readNonEmptyString(
                                                "Enter required skill: ");

                                double minimumCgpa =
                                        InputValidator.readDouble(
                                                "Enter minimum CGPA: ");

                                String opportunityLocation =
                                        InputValidator.readNonEmptyString(
                                                "Enter location: ");

                                double salary =
                                        InputValidator.readDouble(
                                                "Enter salary: ");

                                Opportunity opportunity =
                                        new Opportunity(
                                                companyId,
                                                title,
                                                type,
                                                description,
                                                requiredSkill,
                                                minimumCgpa,
                                                opportunityLocation,
                                                salary
                                        );

                                opportunityService.addOpportunity(
                                        opportunity);

                                break;


                            // VIEW ALL OPPORTUNITIES
                            case 2:

                                System.out.println();
                                System.out.println(
                                        "---------- ALL OPPORTUNITIES ----------");

                                List<Opportunity> opportunities =
                                        opportunityService
                                                .getAllOpportunities();

                                if (opportunities.isEmpty()) {

                                    System.out.println(
                                            "No opportunities found.");

                                } else {

                                    for (Opportunity o : opportunities) {
                                        System.out.println(o);
                                    }
                                }

                                break;


                            // FIND OPPORTUNITY BY ID
                            case 3:

                                System.out.println();

                                int opportunityId =
                                        InputValidator.readInt(
                                                "Enter opportunity ID: ");

                                Opportunity foundOpportunity =
                                        opportunityService
                                                .getOpportunityById(
                                                        opportunityId);

                                if (foundOpportunity != null) {

                                    System.out.println(
                                            foundOpportunity);

                                } else {

                                    System.out.println(
                                            "Opportunity not found.");
                                }

                                break;


                            // UPDATE OPPORTUNITY
                            case 4:

                                System.out.println();

                                int updateOpportunityId =
                                        InputValidator.readInt(
                                                "Enter opportunity ID: ");

                                Opportunity existingOpportunity =
                                        opportunityService
                                                .getOpportunityById(
                                                        updateOpportunityId);

                                if (existingOpportunity == null) {

                                    System.out.println(
                                            "Opportunity not found.");

                                    break;
                                }

                                System.out.println("Enter new details:");

                                int newCompanyId =
                                        InputValidator.readInt(
                                                "Enter company ID: ");

                                String newTitle =
                                        InputValidator.readNonEmptyString(
                                                "Enter title: ");

                                String newType =
                                        InputValidator.readNonEmptyString(
                                                "Enter type: ");

                                String newDescription =
                                        InputValidator.readNonEmptyString(
                                                "Enter description: ");

                                String newRequiredSkill =
                                        InputValidator.readNonEmptyString(
                                                "Enter required skill: ");

                                double newMinimumCgpa =
                                        InputValidator.readDouble(
                                                "Enter minimum CGPA: ");

                                String newOpportunityLocation =
                                        InputValidator.readNonEmptyString(
                                                "Enter location: ");

                                double newSalary =
                                        InputValidator.readDouble(
                                                "Enter salary: ");

                                existingOpportunity.setOpportunityId(
                                        updateOpportunityId);

                                existingOpportunity.setCompanyId(
                                        newCompanyId);

                                existingOpportunity.setTitle(
                                        newTitle);

                                existingOpportunity.setType(
                                        newType);

                                existingOpportunity.setDescription(
                                        newDescription);

                                existingOpportunity.setRequiredSkill(
                                        newRequiredSkill);

                                existingOpportunity.setMinimumCgpa(
                                        newMinimumCgpa);

                                existingOpportunity.setLocation(
                                        newOpportunityLocation);

                                existingOpportunity.setSalary(
                                        newSalary);

                                opportunityService.updateOpportunity(
                                        existingOpportunity);

                                break;


                            // DELETE OPPORTUNITY
                            case 5:

                                System.out.println();

                                int deleteOpportunityId =
                                        InputValidator.readInt(
                                                "Enter opportunity ID: ");

                                opportunityService.deleteOpportunity(
                                        deleteOpportunityId);

                                break;


                            // BACK
                            case 6:
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (opportunityChoice == 6) {
                            break;
                        }
                    }

                    break;


                // =====================================================
                // APPLICATION MANAGEMENT
                // =====================================================

                case 4:

                    while (true) {

                        System.out.println();
                        System.out.println("1) Add Application");
                        System.out.println("2) View All Applications");
                        System.out.println("3) Find Application By ID");
                        System.out.println("4) Update Application Status");
                        System.out.println("5) Check Student Eligibility");
                        System.out.println("6) Delete Application");
                        System.out.println("7) Back");
                        System.out.println("========================================");

                        int applicationChoice =
                                InputValidator.readInt(
                                        "Enter your choice: ");

                        switch (applicationChoice) {

                            // =========================================
                            // ADD APPLICATION
                            // =========================================

                            // =========================================
                            // ADD APPLICATION WITH ELIGIBILITY CHECK
                            // =========================================

                            case 1:

                                System.out.println();
                                System.out.println(
                                        "---------- ADD APPLICATION ----------");

                                int applicationStudentId =
                                        InputValidator.readInt(
                                                "Enter student ID: ");

                                int applicationOpportunityId =
                                        InputValidator.readInt(
                                                "Enter opportunity ID: ");

                                // -----------------------------------------
                                // CHECK STUDENT
                                // -----------------------------------------

                                Student applicationStudent =
                                        studentService.getStudentById(
                                                applicationStudentId);

                                if (applicationStudent == null) {

                                    System.out.println("Student not found.");
                                    break;
                                }

                                // -----------------------------------------
                                // CHECK OPPORTUNITY
                                // -----------------------------------------

                                Opportunity applicationOpportunity =
                                        opportunityService.getOpportunityById(
                                                applicationOpportunityId);

                                if (applicationOpportunity == null) {

                                    System.out.println("Opportunity not found.");
                                    break;
                                }

                                // -----------------------------------------
                                // CHECK ELIGIBILITY
                                // -----------------------------------------

                                boolean eligible =
                                        eligibilityService.isEligible(
                                                applicationStudent,
                                                applicationOpportunity);

                                boolean alreadyApplied =
                                        applicationService.hasAlreadyApplied(
                                                applicationStudentId,
                                                applicationOpportunityId);
                                if (alreadyApplied) {

                                    System.out.println();
                                    System.out.println(
                                            "Student has already applied for this opportunity.");

                                    System.out.println(
                                            "Application cannot be submitted again.");

                                    break;
                                }

                                System.out.println();
                                System.out.println("---------- ELIGIBILITY CHECK ----------");

                                System.out.println(
                                        "Student: " +
                                                applicationStudent.getName());

                                System.out.println(
                                        "Student CGPA: " +
                                                applicationStudent.getCgpa());

                                System.out.println(
                                        "Opportunity: " +
                                                applicationOpportunity.getTitle());

                                System.out.println(
                                        "Minimum CGPA: " +
                                                applicationOpportunity.getMinimumCgpa());

                                if (!eligible) {

                                    System.out.println(
                                            "Result: NOT ELIGIBLE");

                                    System.out.println(
                                            "Application cannot be submitted.");

                                    break;
                                }

                                System.out.println(
                                        "Result: ELIGIBLE");

                                // -----------------------------------------
                                // APPLICATION DATE
                                // -----------------------------------------

                                String dateInput =
                                        InputValidator.readNonEmptyString(
                                                "Enter application date (YYYY-MM-DD): ");

                                LocalDate applicationDate;

                                try {

                                    applicationDate =
                                            LocalDate.parse(dateInput);

                                } catch (Exception e) {

                                    System.out.println(
                                            "Invalid date format. Use YYYY-MM-DD.");

                                    break;
                                }

                                // -----------------------------------------
                                // CREATE APPLICATION
                                // -----------------------------------------

                                Application application =
                                        new Application(
                                                applicationStudentId,
                                                applicationOpportunityId,
                                                applicationDate,
                                                "APPLIED"
                                        );

                                applicationService.addApplication(application);

                                break;


                            // =========================================
                            // VIEW ALL APPLICATIONS
                            // =========================================

                            case 2:

                                System.out.println();
                                System.out.println(
                                        "---------- ALL APPLICATIONS ----------");

                                List<Application> applications =
                                        applicationService
                                                .getAllApplications();

                                if (applications.isEmpty()) {

                                    System.out.println(
                                            "No applications found.");

                                } else {

                                    for (Application a : applications) {
                                        System.out.println(a);
                                    }
                                }

                                break;


                            // =========================================
                            // FIND APPLICATION BY ID
                            // =========================================

                            case 3:

                                System.out.println();
                                System.out.println(
                                        "---------- FIND APPLICATION ----------");

                                int findApplicationId =
                                        InputValidator.readInt(
                                                "Enter application ID: ");

                                Application foundApplication =
                                        applicationService
                                                .getApplicationById(
                                                        findApplicationId);

                                if (foundApplication != null) {

                                    System.out.println(
                                            foundApplication);

                                } else {

                                    System.out.println(
                                            "Application not found.");
                                }

                                break;


                            /// =========================================
// UPDATE APPLICATION STATUS
// =========================================

                            case 4:

                                System.out.println();
                                System.out.println(
                                        "---------- UPDATE APPLICATION ----------");

                                int updateApplicationId =
                                        InputValidator.readInt(
                                                "Enter application ID: ");

                                System.out.println();
                                System.out.println("Available Status:");
                                System.out.println("1. APPLIED");
                                System.out.println("2. SHORTLISTED");
                                System.out.println("3. REJECTED");
                                System.out.println("4. SELECTED");

                                int statusChoice =
                                        InputValidator.readInt(
                                                "Enter status choice: ");

                                String status = null;

                                switch (statusChoice) {

                                    case 1:
                                        status = "APPLIED";
                                        break;

                                    case 2:
                                        status = "SHORTLISTED";
                                        break;

                                    case 3:
                                        status = "REJECTED";
                                        break;

                                    case 4:
                                        status = "SELECTED";
                                        break;

                                    default:
                                        System.out.println("Invalid status choice.");
                                        break;
                                }

                                if (status == null) {
                                    break;
                                }

                                Application updateApplication =
                                        new Application();

                                updateApplication.setApplicationId(
                                        updateApplicationId);

                                updateApplication.setStatus(status);

                                applicationService.updateApplication(
                                        updateApplication);

                                break;

                            // =========================================
                            // DELETE APPLICATION
                            // =========================================

                            // =========================================
// CHECK STUDENT ELIGIBILITY
// =========================================

                            case 5:

                                System.out.println();
                                System.out.println(
                                        "---------- CHECK ELIGIBILITY ----------");

                                int eligibilityStudentId =
                                        InputValidator.readInt(
                                                "Enter student ID: ");

                                int eligibilityOpportunityId =
                                        InputValidator.readInt(
                                                "Enter opportunity ID: ");

                                Student eligibilityStudent =
                                        studentService.getStudentById(
                                                eligibilityStudentId);

                                Opportunity eligibilityOpportunity =
                                        opportunityService.getOpportunityById(
                                                eligibilityOpportunityId);

                                eligibilityService.checkEligibility(
                                        eligibilityStudent,
                                        eligibilityOpportunity);

                                break;


// =========================================
// DELETE APPLICATION
// =========================================

                            case 6:

                                System.out.println();
                                System.out.println(
                                        "---------- DELETE APPLICATION ----------");

                                int deleteApplicationId =
                                        InputValidator.readInt(
                                                "Enter application ID: ");

                                applicationService.deleteApplication(
                                        deleteApplicationId);

                                break;


// =========================================
// BACK
// =========================================

                            case 7:
                                break;



                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (applicationChoice == 7) {
                            break;
                        }
                    }

                    break;


                // =====================================================
                // EXIT
                // =====================================================

                case 5:

                    System.out.println();
                    System.out.println(
                            "Thank you for using the system!");

                    return;


                default:

                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
    }
}