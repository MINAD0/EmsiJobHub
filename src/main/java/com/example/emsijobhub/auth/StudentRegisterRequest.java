package com.example.emsijobhub.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

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
    private Date birth;
    private String jobTitle;
    private String description;
    private String bio;
    private String profile;
    private String resume;
}
