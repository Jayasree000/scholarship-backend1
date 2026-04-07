package com.scholarship.platform.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scholarship_applications")
public class ScholarshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;

    private LocalDateTime appliedAt;
    private String status; // PENDING, APPROVED, REJECTED

    @Column(columnDefinition = "TEXT")
    private String coverLetter;

    private String documents; // comma-separated document URLs

    public ScholarshipApplication() {}

    public ScholarshipApplication(Long id, User student, Scholarship scholarship,
                                   LocalDateTime appliedAt, String status,
                                   String coverLetter, String documents) {
        this.id = id;
        this.student = student;
        this.scholarship = scholarship;
        this.appliedAt = appliedAt;
        this.status = status;
        this.coverLetter = coverLetter;
        this.documents = documents;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public Scholarship getScholarship() { return scholarship; }
    public void setScholarship(Scholarship scholarship) { this.scholarship = scholarship; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

    public String getDocuments() { return documents; }
    public void setDocuments(String documents) { this.documents = documents; }
}
