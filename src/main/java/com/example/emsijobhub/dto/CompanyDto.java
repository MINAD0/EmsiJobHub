package com.example.emsijobhub.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CompanyDto {
    private String name;
    private String rhName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String webSite;
    private MultipartFile profile;

}
