package com.leucine.cda.repository;

import com.leucine.cda.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Additional query methods if needed
}
