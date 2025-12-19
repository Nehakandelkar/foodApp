package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;
}
