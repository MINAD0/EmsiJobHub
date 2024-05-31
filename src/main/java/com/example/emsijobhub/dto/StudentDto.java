package com.example.emsijobhub.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class StudentDto {
    private String name;
    private String email;
    private String password;
    private String registrationNumber;
    private String phone;
    private String address;
    private String description;
    private String bio;
    private Date birth;
    private String gender;
    private MultipartFile cv;
}
