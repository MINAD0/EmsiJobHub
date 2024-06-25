package com.example.emsijobhub.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
    private Long userId;
    private String userType;


    public AuthenticationResponse(String token, Long userId, String userType) {
        this.token = token;
        this.userId = userId;
        this.userType = userType;
    }
}
