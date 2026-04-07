package com.scholarship.platform.service;

import com.scholarship.platform.entity.Scholarship;
import com.scholarship.platform.entity.ScholarshipApplication;
import com.scholarship.platform.repository.ScholarshipApplicationRepository;
import com.scholarship.platform.repository.ScholarshipRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;
    private final ScholarshipApplicationRepository applicationRepository;

    public ScholarshipService(ScholarshipRepository scholarshipRepository,
                               ScholarshipApplicationRepository applicationRepository) {
        this.scholarshipRepository = scholarshipRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    public Optional<Scholarship> getScholarshipById(Long id) {
        return scholarshipRepository.findById(id);
    }

    public Scholarship createScholarship(Scholarship scholarship) {
        if (scholarship.getStatus() == null || scholarship.getStatus().isEmpty()) {
            scholarship.setStatus("ACTIVE");
        }
        return scholarshipRepository.save(scholarship);
    }

    public Scholarship updateScholarship(Long id, Scholarship updated) {
        return scholarshipRepository.findById(id).map(s -> {
            s.setTitle(updated.getTitle());
            s.setDescription(updated.getDescription());
            s.setAmount(updated.getAmount());
            s.setDeadline(updated.getDeadline());
            s.setEligibilityCriteria(updated.getEligibilityCriteria());
            s.setCategory(updated.getCategory());
            s.setStatus(updated.getStatus());
            return scholarshipRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Scholarship not found with id: " + id));
    }

    public void deleteScholarship(Long id) {
        scholarshipRepository.deleteById(id);
    }

    public ScholarshipApplication applyForScholarship(ScholarshipApplication application) {
        application.setAppliedAt(LocalDateTime.now());
        application.setStatus("PENDING");
        return applicationRepository.save(application);
    }

    public List<ScholarshipApplication> getAllApplications() {
        return applicationRepository.findAll();
    }

    public List<ScholarshipApplication> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    public List<ScholarshipApplication> getApplicationsByScholarship(Long scholarshipId) {
        return applicationRepository.findByScholarshipId(scholarshipId);
    }

    public ScholarshipApplication updateApplicationStatus(Long appId, String status) {
        return applicationRepository.findById(appId).map(app -> {
            app.setStatus(status);
            return applicationRepository.save(app);
        }).orElseThrow(() -> new RuntimeException("Application not found with id: " + appId));
    }
}
