package com.example.schoolservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolDto {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
