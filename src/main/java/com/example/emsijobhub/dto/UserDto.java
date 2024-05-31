package com.example.emsijobhub.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class UserDto {
    private String email;
    private String password;
    private String phone;
    private String address;
    private String gender;
    private MultipartFile profile;
}
