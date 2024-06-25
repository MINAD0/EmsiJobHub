package com.example.emsijobhub.dto;

import com.example.emsijobhub.dao.entities.Role;
import com.example.emsijobhub.dao.entities.Student;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String gender;
    private String role;
    private String jobTitle;
    private String profile;
    private String resume;


    public Student toEntity() {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setRegistrationNb(registrationNb);
        student.setPhone(phone);
        student.setAddress(address);
        student.setDescription(description);
        student.setBio(bio);
        student.setBirth(birth);
        student.setGender(gender);
        student.setRole(Role.valueOf(role)); // Assuming role is set externally
        student.setJobTitle(jobTitle);
        student.setProfile(profile);
        student.setResume(resume);
        return student;
    }
}
