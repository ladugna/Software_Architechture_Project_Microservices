package com.example.studentservice.DTO;

public class StudentClassDTO {
    private String year;
    private String group;

    public StudentClassDTO() {
        // Default constructor for Jackson
    }

    public StudentClassDTO(String year, String group) {
        this.year = year;
        this.group = group;
    }

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
