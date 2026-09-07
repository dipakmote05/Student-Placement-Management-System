package com.placementportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterCompanyRequest(
        @Email @NotBlank String email,
        @NotBlank @Size(min = 8, message = "must contain at least 8 characters") String password,
        @NotBlank String companyName,
        String industry,
        String website,
        String description
) {
}
