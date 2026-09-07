package com.placementportal.dto;

import com.placementportal.models.Opportunity;

import java.time.LocalDate;

public record OpportunityResponse(
        Long id,
        Long companyId,
        String companyName,
        String title,
        String description,
        String industry,
        String location,
        String requiredSkills,
        Double stipend,
        LocalDate applicationDeadline,
        Integer currentApplicants,
        Integer maxApplicants
) {
    public static OpportunityResponse from(Opportunity opportunity) {
        return new OpportunityResponse(
                opportunity.getId(),
                opportunity.getCompany().getId(),
                opportunity.getCompany().getCompanyName(),
                opportunity.getTitle(),
                opportunity.getDescription(),
                opportunity.getIndustry(),
                opportunity.getLocation(),
                opportunity.getRequiredSkills(),
                opportunity.getStipend(),
                opportunity.getApplicationDeadline(),
                opportunity.getCurrentApplicants(),
                opportunity.getMaxApplicants()
        );
    }
}
