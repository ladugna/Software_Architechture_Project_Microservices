package com.example.teacherservice.DTO;

import com.example.teacherservice.DTO.ContactDTO;
import com.example.teacherservice.DTO.TeachingClassDTO;
import lombok.Data;

@Data
public class TeacherDTO {
    private String id;
    private String firstName;
    private String lastName;
    private ContactDTO contact;
    private String school;
    private TeachingClassDTO teachingClass;
}
