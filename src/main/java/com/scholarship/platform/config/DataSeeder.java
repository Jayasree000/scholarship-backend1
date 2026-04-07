package com.scholarship.platform.config;

import com.scholarship.platform.entity.Scholarship;
import com.scholarship.platform.entity.User;
import com.scholarship.platform.repository.ScholarshipRepository;
import com.scholarship.platform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(UserRepository userRepository,
                                      ScholarshipRepository scholarshipRepository) {
        return args -> {
            // Only seed if empty
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setName("Admin User");
                admin.setEmail("admin@scholarship.com");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);

                User student1 = new User();
                student1.setName("Priya Sharma");
                student1.setEmail("priya@student.com");
                student1.setPassword("student123");
                student1.setRole("STUDENT");
                userRepository.save(student1);

                User student2 = new User();
                student2.setName("Rahul Verma");
                student2.setEmail("rahul@student.com");
                student2.setPassword("student123");
                student2.setRole("STUDENT");
                userRepository.save(student2);

                User sponsor = new User();
                sponsor.setName("TechCorp Foundation");
                sponsor.setEmail("sponsor@techcorp.com");
                sponsor.setPassword("sponsor123");
                sponsor.setRole("SPONSOR");
                userRepository.save(sponsor);

                System.out.println("✅ Users seeded successfully!");
            }

            if (scholarshipRepository.count() == 0) {
                User admin = userRepository.findByEmail("admin@scholarship.com").orElse(null);

                Scholarship s1 = new Scholarship();
                s1.setTitle("National Merit Scholarship");
                s1.setDescription("Awarded to students who demonstrate exceptional academic achievement and leadership qualities.");
                s1.setAmount(new BigDecimal("50000"));
                s1.setDeadline(LocalDate.of(2026, 6, 30));
                s1.setEligibilityCriteria("GPA 3.8+, Indian citizen, undergraduate student");
                s1.setCategory("MERIT");
                s1.setStatus("ACTIVE");
                s1.setSponsor(admin);
                scholarshipRepository.save(s1);

                Scholarship s2 = new Scholarship();
                s2.setTitle("Need-Based Financial Aid");
                s2.setDescription("Supporting students from low-income families to pursue higher education without financial barriers.");
                s2.setAmount(new BigDecimal("30000"));
                s2.setDeadline(LocalDate.of(2026, 7, 15));
                s2.setEligibilityCriteria("Annual family income below 3 LPA, undergraduate student");
                s2.setCategory("NEED_BASED");
                s2.setStatus("ACTIVE");
                s2.setSponsor(admin);
                scholarshipRepository.save(s2);

                Scholarship s3 = new Scholarship();
                s3.setTitle("Women in STEM Scholarship");
                s3.setDescription("Empowering women to pursue careers in Science, Technology, Engineering and Mathematics fields.");
                s3.setAmount(new BigDecimal("75000"));
                s3.setDeadline(LocalDate.of(2026, 8, 1));
                s3.setEligibilityCriteria("Female students enrolled in STEM courses");
                s3.setCategory("MERIT");
                s3.setStatus("ACTIVE");
                s3.setSponsor(admin);
                scholarshipRepository.save(s3);

                Scholarship s4 = new Scholarship();
                s4.setTitle("Sports Excellence Award");
                s4.setDescription("For students who have represented their state or nation in competitive sports.");
                s4.setAmount(new BigDecimal("25000"));
                s4.setDeadline(LocalDate.of(2026, 5, 31));
                s4.setEligibilityCriteria("State or national level sports representation");
                s4.setCategory("SPORTS");
                s4.setStatus("ACTIVE");
                s4.setSponsor(admin);
                scholarshipRepository.save(s4);

                System.out.println("✅ Scholarships seeded successfully!");
            }
        };
    }
}
