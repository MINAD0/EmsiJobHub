package com.example.emsijobhub.services;

import com.example.emsijobhub.dao.entities.Student;
import com.example.emsijobhub.dao.entities.User;
import com.example.emsijobhub.dao.repositories.StudentRepository;
import com.example.emsijobhub.dao.repositories.UserRepository;
import com.example.emsijobhub.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        // Update existingStudent with fields from studentDto
        existingStudent.setName(studentDto.getName());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.setRegistrationNb(studentDto.getRegistrationNb());
        existingStudent.setPhone(studentDto.getPhone());
        existingStudent.setAddress(studentDto.getAddress());
        existingStudent.setDescription(studentDto.getDescription());
        existingStudent.setBio(studentDto.getBio());
        existingStudent.setBirth(studentDto.getBirth());
        existingStudent.setGender(studentDto.getGender());
        existingStudent.setJobTitle(studentDto.getJobTitle());
        existingStudent.setProfile(studentDto.getProfile());
        existingStudent.setResume(studentDto.getResume());

        return studentRepository.save(existingStudent);
    }

}
