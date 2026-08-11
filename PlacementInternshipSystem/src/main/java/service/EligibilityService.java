package service;

import model.Student;
import model.Opportunity;

public class EligibilityService {

    public boolean isEligible(Student student, Opportunity opportunity) {

        if (student == null) {
            System.out.println("Student not found.");
            return false;
        }

        if (opportunity == null) {
            System.out.println("Opportunity not found.");
            return false;
        }

        return student.getCgpa() >= opportunity.getMinimumCgpa();
    }

    public void checkEligibility(Student student,
                                 Opportunity opportunity) {

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (opportunity == null) {
            System.out.println("Opportunity not found.");
            return;
        }

        System.out.println();
        System.out.println("---------- ELIGIBILITY CHECK ----------");

        System.out.println("Student: " + student.getName());
        System.out.println("Student CGPA: " + student.getCgpa());

        System.out.println("Opportunity: " + opportunity.getTitle());
        System.out.println(
                "Minimum CGPA: " + opportunity.getMinimumCgpa()
        );

        if (student.getCgpa() >= opportunity.getMinimumCgpa()) {

            System.out.println("Result: ELIGIBLE");

        } else {

            System.out.println("Result: NOT ELIGIBLE");
        }
    }
}