package com.example.emsijobhub.dto;

import com.example.emsijobhub.dao.entities.Student;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private String registrationNb;
    private String phone;
    private String address;
    private String description;
    private String bio;
    private Date birth;
    private String gender;
    private String role;
    private String jobTitle;
    private String profile;
    private String resume;


    public StudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.phone = student.getPhone();
        this.address = student.getAddress();
        this.gender = student.getGender();
        this.profile = student.getProfile();
        this.resume = student.getResume();
    }
}
