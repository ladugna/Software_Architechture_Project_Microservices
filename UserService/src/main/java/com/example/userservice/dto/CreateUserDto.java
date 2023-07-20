package com.example.userservice.dto;

import com.example.userservice.Domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@NoArgsConstructor
@Data
public class CreateUserDto {
    private String username;
    private String password;
    private Role role;
}
