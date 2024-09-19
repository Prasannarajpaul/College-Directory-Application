package com.leucine.cda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Long departmentId;
    private Long facultyId;
    // Getters and Setters
}
