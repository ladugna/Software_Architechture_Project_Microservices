package com.example.schoolservice.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data

public class School {
    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
