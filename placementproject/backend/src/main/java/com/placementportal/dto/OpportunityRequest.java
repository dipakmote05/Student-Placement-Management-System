package com.placementportal.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record OpportunityRequest(
        @NotNull Long companyId,
        @NotBlank String title,
        String description,
        String industry,
        String location,
        String requiredSkills,
        @PositiveOrZero Double stipend,
        @FutureOrPresent LocalDate applicationDeadline,
        @Positive Integer maxApplicants
) {
}
