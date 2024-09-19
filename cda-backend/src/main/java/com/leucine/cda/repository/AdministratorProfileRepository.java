package com.leucine.cda.repository;

import com.leucine.cda.model.AdministratorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long> {
    // Additional query methods if needed
}
