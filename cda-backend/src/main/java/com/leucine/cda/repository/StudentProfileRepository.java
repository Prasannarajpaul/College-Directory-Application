package com.leucine.cda.repository;

import com.leucine.cda.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    // Additional query methods if needed
}
