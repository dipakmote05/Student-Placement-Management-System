package com.placementportal.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company extends User {

    private String companyName;
    private String industry;
    private String website;
    @jakarta.persistence.Column(length = 5000)
    private String description;

}
