package com.example.emsijobhub.dto;

import com.example.emsijobhub.dao.entities.Company;
import com.example.emsijobhub.dao.entities.Role;
import com.example.emsijobhub.dao.entities.Student;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String rhName;
    private String email;
    private String password;
    private String phone;
    private String description;
    private String address;
    private String role;
    private String webSite;
    private String profile;
    private String city;



    public Company toEntity() {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setEmail(email);
        company.setPhone(phone);
        company.setAddress(address);
        company.setRole(Role.valueOf(role)); // Assuming role is set externally
        company.setProfile(profile);
        company.setRhName(rhName);
        company.setWebSite(webSite);
        company.setCity(city);
        company.setDescription(description);
        return company;
    }

}
