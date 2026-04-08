package com.scholarship.platform.config;

import com.scholarship.platform.entity.Scholarship;
import com.scholarship.platform.entity.ScholarshipApplication;
import com.scholarship.platform.entity.User;
import com.scholarship.platform.repository.ScholarshipApplicationRepository;
import com.scholarship.platform.repository.ScholarshipRepository;
import com.scholarship.platform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(UserRepository userRepository,
                                      ScholarshipRepository scholarshipRepository,
                                      ScholarshipApplicationRepository applicationRepository) {
        return args -> {
            try {
                System.out.println("🌱 Starting FRESH data seeding...");
                
                // CLEAR EVERYTHING TO ENSURE WE SEE THE NEW DATA
                applicationRepository.deleteAll();
                scholarshipRepository.deleteAll();
                userRepository.deleteAll();
                
                // 1. Seed Users (Ensure specific test users exist)
                User admin = userRepository.save(new User(null, "Admin User", "admin@scholarship.com", "admin123", "ADMIN"));
                User priya = userRepository.save(new User(null, "Priya Sharma", "priya@student.com", "student123", "STUDENT"));
                User rahul = userRepository.save(new User(null, "Rahul Verma", "rahul@student.com", "student123", "STUDENT"));


                // 2. Seed Scholarships
                if (scholarshipRepository.count() == 0) {
                    scholarshipRepository.save(new Scholarship(null, "National Merit Scholarship", "Academic excellence award.", new BigDecimal("50000"), LocalDate.now().plusMonths(3), "GPA 3.8+", "MERIT", "ACTIVE", admin));
                    scholarshipRepository.save(new Scholarship(null, "Need-Based Aid", "Financial support.", new BigDecimal("30000"), LocalDate.now().plusMonths(2), "Income < 3LPA", "NEED_BASED", "ACTIVE", admin));
                    scholarshipRepository.save(new Scholarship(null, "Women in STEM", "Empowering women in tech.", new BigDecimal("75000"), LocalDate.now().plusMonths(4), "Female, STEM", "MERIT", "ACTIVE", admin));
                    scholarshipRepository.save(new Scholarship(null, "Sports Award", "Excellence in sports.", new BigDecimal("25000"), LocalDate.now().plusMonths(1), "State level", "SPORTS", "ACTIVE", admin));
                    System.out.println("✅ Scholarships seeded.");
                }

                // 3. Seed Applications
                if (applicationRepository.count() == 0) {
                    List<Scholarship> allS = scholarshipRepository.findAll();
                    if (priya != null && rahul != null && !allS.isEmpty()) {
                        Scholarship s1 = allS.get(0);
                        Scholarship s2 = allS.size() > 1 ? allS.get(1) : s1;
                        Scholarship s3 = allS.size() > 2 ? allS.get(2) : s1;
                        Scholarship s4 = allS.size() > 3 ? allS.get(3) : s1;

                        applicationRepository.save(new ScholarshipApplication(null, priya, s1, LocalDateTime.now(), "PENDING", "Cover letter text for Priya S1", null));
                        applicationRepository.save(new ScholarshipApplication(null, priya, s3, LocalDateTime.now(), "APPROVED", "Cover letter text for Priya S3", null));
                        applicationRepository.save(new ScholarshipApplication(null, rahul, s2, LocalDateTime.now(), "PENDING", "Cover letter text for Rahul S2", null));
                        applicationRepository.save(new ScholarshipApplication(null, rahul, s4, LocalDateTime.now(), "REJECTED", "Cover letter text for Rahul S4", null));
                        applicationRepository.save(new ScholarshipApplication(null, rahul, s1, LocalDateTime.now(), "PENDING", "Cover letter text for Rahul S1", null));
                        applicationRepository.save(new ScholarshipApplication(null, priya, s4, LocalDateTime.now(), "APPROVED", "Cover letter text for Priya S4", null));
                    }
                }
                
                System.out.println("✅ Full Restoration complete!");
            } catch (Exception e) {
                System.err.println("❌ ERROR DURING RESTORATION: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
