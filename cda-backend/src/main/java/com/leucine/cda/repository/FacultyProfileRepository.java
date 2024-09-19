package com.leucine.cda.repository;

import com.leucine.cda.model.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
    // Additional query methods if needed
}
