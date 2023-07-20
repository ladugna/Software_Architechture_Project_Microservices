package com.example.teacherservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAddedEvent {
    private String firstName;
    private String lastName;
    private String school;

}
