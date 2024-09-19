package com.leucine.cda.repository;

import com.leucine.cda.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Additional query methods if needed
}
