package com.leucine.cda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "faculty_profile")
public class FacultyProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String photo;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String officeHours;
}