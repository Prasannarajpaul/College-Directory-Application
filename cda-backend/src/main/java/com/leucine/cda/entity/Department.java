package com.leucine.cda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "department")
    private List<StudentProfile> students;

    @OneToMany(mappedBy = "department")
    private List<FacultyProfile> facultyMembers;

    @OneToMany(mappedBy = "department")
    private List<AdministratorProfile> administrators;

    @OneToMany(mappedBy = "department")
    private List<Course> courses;
}
