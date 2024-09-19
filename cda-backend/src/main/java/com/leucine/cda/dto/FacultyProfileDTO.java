package com.leucine.cda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class FacultyProfileDTO {
    private Long userId;
    private String photo;
    private Long departmentId;
    private String officeHours;
    // Getters and Setters
}
