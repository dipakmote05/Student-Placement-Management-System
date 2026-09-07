package com.placementportal.controllers;

import com.placementportal.dto.ApplicationResponse;
import com.placementportal.models.Application;
import com.placementportal.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<ApplicationResponse> applyForOpportunity(
            @RequestParam Long studentId,
            @RequestParam Long opportunityId) {
        return ResponseEntity.ok(applicationService.applyForOpportunity(studentId, opportunityId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationResponse>> getStudentApplications(@PathVariable Long studentId) {
        return ResponseEntity.ok(applicationService.getStudentApplications(studentId).stream()
                .toList());
    }

    @GetMapping("/opportunity/{opportunityId}")
    public ResponseEntity<List<ApplicationResponse>> getOpportunityApplications(@PathVariable Long opportunityId) {
        return ResponseEntity.ok(applicationService.getOpportunityApplications(opportunityId).stream()
                .toList());
    }

    @PatchMapping("/{studentId}/{opportunityId}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @PathVariable Long studentId,
            @PathVariable Long opportunityId,
            @RequestParam Application.Status status) {
        return ResponseEntity.ok(applicationService.updateStatus(studentId, opportunityId, status));
    }
}
