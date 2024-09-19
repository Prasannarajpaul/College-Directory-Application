package com.leucine.cda.repository;

import com.leucine.cda.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Additional query methods if needed
}
