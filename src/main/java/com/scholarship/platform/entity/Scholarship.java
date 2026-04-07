package com.scholarship.platform.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "scholarships")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal amount;
    private LocalDate deadline;
    private String eligibilityCriteria;
    private String category; // MERIT, NEED_BASED, SPORTS, etc.
    private String status; // ACTIVE, CLOSED

    @ManyToOne
    @JoinColumn(name = "sponsor_id")
    private User sponsor;

    public Scholarship() {}

    public Scholarship(Long id, String title, String description, BigDecimal amount,
                       LocalDate deadline, String eligibilityCriteria, String category,
                       String status, User sponsor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.deadline = deadline;
        this.eligibilityCriteria = eligibilityCriteria;
        this.category = category;
        this.status = status;
        this.sponsor = sponsor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getSponsor() { return sponsor; }
    public void setSponsor(User sponsor) { this.sponsor = sponsor; }
}
