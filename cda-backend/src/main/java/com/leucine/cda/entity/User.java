package com.leucine.cda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String name;
    private String email;
    private String phone;

    @OneToOne(mappedBy = "user")
    private StudentProfile studentProfile;
    @OneToOne(mappedBy = "user")
    private  FacultyProfile facultyProfile;
    @OneToOne(mappedBy = "user")
    private AdministratorProfile administratorProfile;


}
