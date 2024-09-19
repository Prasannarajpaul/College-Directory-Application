package com.leucine.cda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String email;
    private String phone;

    public enum Role {
        STUDENT, FACULTY_MEMBER, ADMINISTRATOR
    }
}