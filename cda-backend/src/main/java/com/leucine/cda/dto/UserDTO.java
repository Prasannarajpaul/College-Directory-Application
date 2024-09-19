package com.leucine.cda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String email;
    private String phone;
    // Getters and Setters

}
