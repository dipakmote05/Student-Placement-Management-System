package com.placementportal.dto;

import com.placementportal.models.Application;

import java.time.LocalDateTime;

public record ApplicationResponse(
        Long studentId,
        Long opportunityId,
        String opportunityTitle,
        Application.Status status,
        LocalDateTime appliedAt
) {
    public static ApplicationResponse from(Application application) {
        return new ApplicationResponse(
                application.getStudent().getId(),
                application.getOpportunity().getId(),
                application.getOpportunity().getTitle(),
                application.getStatus(),
                application.getAppliedAt()
        );
    }
}
