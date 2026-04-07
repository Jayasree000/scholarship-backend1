package com.scholarship.platform.controller;

import com.scholarship.platform.entity.Scholarship;
import com.scholarship.platform.entity.ScholarshipApplication;
import com.scholarship.platform.service.ScholarshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scholarships")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    public ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    // GET /api/scholarships
    @GetMapping
    public List<Scholarship> getAllScholarships() {
        return scholarshipService.getAllScholarships();
    }

    // GET /api/scholarships/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Scholarship> getScholarshipById(@PathVariable Long id) {
        return scholarshipService.getScholarshipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/scholarships
    @PostMapping
    public ResponseEntity<Scholarship> createScholarship(@RequestBody Scholarship scholarship) {
        return ResponseEntity.ok(scholarshipService.createScholarship(scholarship));
    }

    // PUT /api/scholarships/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Scholarship> updateScholarship(@PathVariable Long id,
                                                          @RequestBody Scholarship scholarship) {
        return ResponseEntity.ok(scholarshipService.updateScholarship(id, scholarship));
    }

    // DELETE /api/scholarships/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScholarship(@PathVariable Long id) {
        scholarshipService.deleteScholarship(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/scholarships/apply
    @PostMapping("/apply")
    public ResponseEntity<ScholarshipApplication> apply(@RequestBody ScholarshipApplication application) {
        return ResponseEntity.ok(scholarshipService.applyForScholarship(application));
    }

    // GET /api/scholarships/applications
    @GetMapping("/applications")
    public List<ScholarshipApplication> getAllApplications() {
        return scholarshipService.getAllApplications();
    }

    // GET /api/scholarships/applications/student/{studentId}
    @GetMapping("/applications/student/{studentId}")
    public List<ScholarshipApplication> getApplicationsByStudent(@PathVariable Long studentId) {
        return scholarshipService.getApplicationsByStudent(studentId);
    }

    // GET /api/scholarships/applications/scholarship/{scholarshipId}
    @GetMapping("/applications/scholarship/{scholarshipId}")
    public List<ScholarshipApplication> getApplicationsByScholarship(@PathVariable Long scholarshipId) {
        return scholarshipService.getApplicationsByScholarship(scholarshipId);
    }

    // PATCH /api/scholarships/applications/{appId}/status
    @PatchMapping("/applications/{appId}/status")
    public ResponseEntity<ScholarshipApplication> updateStatus(@PathVariable Long appId,
                                                                 @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return ResponseEntity.ok(scholarshipService.updateApplicationStatus(appId, status));
    }
}
