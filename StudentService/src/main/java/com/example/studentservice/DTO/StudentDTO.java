package com.example.studentservice.DTO;

import com.example.studentservice.DTO.StudentClassDTO;
import com.example.studentservice.model.Avatar;
import com.example.studentservice.model.Element;
import com.example.studentservice.model.RewardItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String studentNumber;
    private String school;
    private StudentClassDTO studentClassDTO;
    private double score;
    private Avatar avatar;//needs to change
    private List<RewardItem> rewards;
    private List<Element> elements= new ArrayList<>();


}
