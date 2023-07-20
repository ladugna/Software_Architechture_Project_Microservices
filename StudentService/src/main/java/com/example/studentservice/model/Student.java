package com.example.studentservice.model;


import com.example.studentservice.DTO.StudentClassDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String studentNumber;
    private String school;
    private StudentClassDTO studentClassDTO;
    private double score;
    private Avatar avatar;
    private List<RewardItem> rewards;
    private List<Element> elements= new ArrayList<>();

}
