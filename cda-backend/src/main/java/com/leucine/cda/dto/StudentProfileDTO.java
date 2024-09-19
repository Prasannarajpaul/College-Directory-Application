package com.leucine.cda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileDTO {
    private Long userId;
    private String photo;
    private Long departmentId;
    private String year;
    // Getters and Setters
}
