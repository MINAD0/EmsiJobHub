package com.example.emsijobhub.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String profile;
}
