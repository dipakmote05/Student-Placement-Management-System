package com.placementportal.controllers;

import com.placementportal.dto.OpportunityRequest;
import com.placementportal.dto.OpportunityResponse;
import com.placementportal.services.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    @PostMapping
    public ResponseEntity<OpportunityResponse> createOpportunity(@Valid @RequestBody OpportunityRequest request) {
        return ResponseEntity.ok(OpportunityResponse.from(opportunityService.createOpportunity(request)));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<OpportunityResponse>> searchOpportunities(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String industry,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        int safeSize = Math.min(Math.max(size, 1), 100);
        Page<OpportunityResponse> result = opportunityService
                .searchOpportunities(keyword, location, industry, PageRequest.of(Math.max(page, 0), safeSize));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpportunityResponse> getOpportunity(@PathVariable Long id) {
        return ResponseEntity.ok(opportunityService.getOpportunity(id));
    }
}
