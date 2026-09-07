package com.placementportal.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "opportunities", indexes = {
    @Index(name = "idx_industry", columnList = "industry"),
    @Index(name = "idx_location", columnList = "location")
})
@Getter
@Setter
@NoArgsConstructor
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String title;
    @Column(length = 5000)
    private String description;
    
    private String industry;
    private String location;
    
    private String requiredSkills;
    private Double stipend;
    
    private LocalDate applicationDeadline;
    
    @Column(nullable = false)
    private Integer currentApplicants = 0;
    
    private Integer maxApplicants;

}
