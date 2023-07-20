package com.example.studentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Element {
    private String id;
    private ElementType type;
    private double price;
}
