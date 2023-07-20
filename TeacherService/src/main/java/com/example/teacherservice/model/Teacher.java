package com.example.teacherservice.model;

import com.example.teacherservice.DTO.ContactDTO;
import com.example.teacherservice.DTO.TeachingClassDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Teacher {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private ContactDTO contact;
    private String school;
    private TeachingClassDTO teachingClass;
}
