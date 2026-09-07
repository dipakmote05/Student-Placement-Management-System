package com.placementportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterStudentRequest(
        @Email @NotBlank String email,
        @NotBlank @Size(min = 8, message = "must contain at least 8 characters") String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        String university,
        String major,
        Double gpa,
        String technicalSkills,
        String resumeUrl
) {
}
