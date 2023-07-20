package com.example.studentservice.DTO;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class RewardItemDTO {
    private String name;
    private int value;
}
