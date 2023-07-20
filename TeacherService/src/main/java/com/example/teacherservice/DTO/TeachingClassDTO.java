package com.example.teacherservice.DTO;


import lombok.Data;

@Data
public class TeachingClassDTO {
    private String year;
    private String group;
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
