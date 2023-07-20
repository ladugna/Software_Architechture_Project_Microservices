package com.example.userservice.Domain;



public enum Role {
    Admin ("admin"), Teacher("teacher"), Student("student");

    private final String value;

    Role(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

