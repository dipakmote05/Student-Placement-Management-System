package com.placementportal.repositories;

import com.placementportal.models.Application;
import com.placementportal.models.ApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    List<Application> findByStudent_Id(Long studentId);
    List<Application> findByOpportunity_Id(Long opportunityId);
}
