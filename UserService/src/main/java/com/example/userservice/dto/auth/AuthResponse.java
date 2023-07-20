package com.example.userservice.dto.auth;

import lombok.Data;

@Data
public class AuthResponse {

    private final String accessToken;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
