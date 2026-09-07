package com.placementportal.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student extends User {

    private String firstName;
    private String lastName;
    private String university;
    private String major;
    private Double gpa;
    private String technicalSkills; // Comma separated or JSON in a real app
    private String resumeUrl;

}
