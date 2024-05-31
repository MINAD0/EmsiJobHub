package com.example.emsijobhub.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String registrationNb;
    private String phone;
    private String address;
    private String gender;
    private String profile;
    private String resume;
}
