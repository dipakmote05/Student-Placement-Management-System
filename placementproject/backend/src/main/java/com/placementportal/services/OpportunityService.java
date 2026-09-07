package com.placementportal.services;

import com.placementportal.dto.OpportunityRequest;
import com.placementportal.dto.OpportunityResponse;
import com.placementportal.models.Company;
import com.placementportal.models.Opportunity;
import com.placementportal.repositories.CompanyRepository;
import com.placementportal.repositories.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Opportunity createOpportunity(OpportunityRequest request) {
        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        Opportunity opportunity = new Opportunity();
        opportunity.setCompany(company);
        opportunity.setTitle(request.title());
        opportunity.setDescription(request.description());
        opportunity.setIndustry(request.industry());
        opportunity.setLocation(request.location());
        opportunity.setRequiredSkills(request.requiredSkills());
        opportunity.setStipend(request.stipend());
        opportunity.setApplicationDeadline(request.applicationDeadline());
        opportunity.setMaxApplicants(request.maxApplicants());
        opportunity.setCurrentApplicants(0);
        return opportunityRepository.save(opportunity);
    }

    @Transactional(readOnly = true)
    public Page<OpportunityResponse> searchOpportunities(String keyword, String location, String industry, Pageable pageable) {
        Specification<Opportunity> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"));
            }
            if (location != null && !location.isEmpty()) {
                predicates.add(cb.equal(root.get("location"), location));
            }
            if (industry != null && !industry.isEmpty()) {
                predicates.add(cb.equal(root.get("industry"), industry));
            }
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        
        return opportunityRepository.findAll(spec, pageable).map(OpportunityResponse::from);
    }

    @Transactional(readOnly = true)
    public OpportunityResponse getOpportunity(Long id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Opportunity not found"));
        return OpportunityResponse.from(opportunity);
    }
}
