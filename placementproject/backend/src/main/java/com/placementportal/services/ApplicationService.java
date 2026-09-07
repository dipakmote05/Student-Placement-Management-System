package com.placementportal.services;

import com.placementportal.dto.ApplicationResponse;
import com.placementportal.models.Application;
import com.placementportal.models.ApplicationId;
import com.placementportal.models.Opportunity;
import com.placementportal.models.Student;
import com.placementportal.repositories.ApplicationRepository;
import com.placementportal.repositories.OpportunityRepository;
import com.placementportal.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public ApplicationResponse applyForOpportunity(Long studentId, Long opportunityId) {
        // Fetch the opportunity WITH a pessimistic write lock to prevent race conditions
        Opportunity opportunity = opportunityRepository.findByIdForUpdate(opportunityId)
                .orElseThrow(() -> new IllegalArgumentException("Opportunity not found"));

        if (opportunity.getApplicationDeadline() != null
                && opportunity.getApplicationDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The application deadline has passed");
        }

        if (opportunity.getMaxApplicants() != null && opportunity.getCurrentApplicants() >= opportunity.getMaxApplicants()) {
            throw new IllegalArgumentException("Maximum applicants reached for this opportunity");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        ApplicationId appId = new ApplicationId(studentId, opportunityId);
        
        if (applicationRepository.existsById(appId)) {
            throw new IllegalArgumentException("You have already applied for this opportunity");
        }

        Application application = new Application();
        application.setId(appId);
        application.setStudent(student);
        application.setOpportunity(opportunity);
        application.setStatus(Application.Status.PENDING);
        application.setAppliedAt(LocalDateTime.now());

        // Increment applicant count safely inside the transaction
        opportunity.setCurrentApplicants(opportunity.getCurrentApplicants() + 1);
        opportunityRepository.save(opportunity);

        return ApplicationResponse.from(applicationRepository.save(application));
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> getStudentApplications(Long studentId) {
        return applicationRepository.findByStudent_Id(studentId).stream()
                .map(ApplicationResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> getOpportunityApplications(Long opportunityId) {
        return applicationRepository.findByOpportunity_Id(opportunityId).stream()
                .map(ApplicationResponse::from)
                .toList();
    }

    @Transactional
    public ApplicationResponse updateStatus(Long studentId, Long opportunityId, Application.Status status) {
        Application application = applicationRepository.findById(new ApplicationId(studentId, opportunityId))
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        application.setStatus(status);
        return ApplicationResponse.from(applicationRepository.save(application));
    }
}
