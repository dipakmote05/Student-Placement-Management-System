package com.placementportal.services;

import com.placementportal.dto.LoginRequest;
import com.placementportal.dto.RegisterCompanyRequest;
import com.placementportal.dto.RegisterStudentRequest;
import com.placementportal.dto.UserResponse;
import com.placementportal.models.Company;
import com.placementportal.models.Student;
import com.placementportal.models.User;
import com.placementportal.repositories.CompanyRepository;
import com.placementportal.repositories.StudentRepository;
import com.placementportal.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, StudentRepository studentRepository,
                       CompanyRepository companyRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse registerStudent(RegisterStudentRequest request) {
        ensureEmailAvailable(request.email());
        Student student = new Student();
        student.setEmail(request.email().trim().toLowerCase());
        student.setPasswordHash(passwordEncoder.encode(request.password()));
        student.setRole(User.Role.STUDENT);
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setUniversity(request.university());
        student.setMajor(request.major());
        student.setGpa(request.gpa());
        student.setTechnicalSkills(request.technicalSkills());
        student.setResumeUrl(request.resumeUrl());
        Student saved = studentRepository.save(student);
        return new UserResponse(saved.getId(), saved.getEmail(), saved.getRole(),
                saved.getFirstName() + " " + saved.getLastName());
    }

    @Transactional
    public UserResponse registerCompany(RegisterCompanyRequest request) {
        ensureEmailAvailable(request.email());
        Company company = new Company();
        company.setEmail(request.email().trim().toLowerCase());
        company.setPasswordHash(passwordEncoder.encode(request.password()));
        company.setRole(User.Role.RECRUITER);
        company.setCompanyName(request.companyName());
        company.setIndustry(request.industry());
        company.setWebsite(request.website());
        company.setDescription(request.description());
        Company saved = companyRepository.save(company);
        return new UserResponse(saved.getId(), saved.getEmail(), saved.getRole(), saved.getCompanyName());
    }

    @Transactional(readOnly = true)
    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email().trim().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String displayName = user.getEmail();
        if (user instanceof Student student) {
            displayName = student.getFirstName() + " " + student.getLastName();
        } else if (user instanceof Company company) {
            displayName = company.getCompanyName();
        }
        return new UserResponse(user.getId(), user.getEmail(), user.getRole(), displayName);
    }

    private void ensureEmailAvailable(String email) {
        if (userRepository.findByEmail(email.trim().toLowerCase()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }
    }
}
