package com.placementportal.repositories;

import com.placementportal.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import java.util.Optional;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long>, JpaSpecificationExecutor<Opportunity> {
    
    // Pessimistic write lock to prevent concurrent application limits from being exceeded
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM Opportunity o WHERE o.id = :id")
    Optional<Opportunity> findByIdForUpdate(@Param("id") Long id);
}
