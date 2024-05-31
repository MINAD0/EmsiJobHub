package com.example.emsijobhub.services;

import com.example.emsijobhub.dao.entities.Student;
import com.example.emsijobhub.dao.entities.User;
import com.example.emsijobhub.dao.repositories.UserRepository;
import com.example.emsijobhub.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String UPLOAD_DIR = "uploads/";

    public User registerStudent(StudentDto studentDto){
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPasswd(passwordEncoder.encode(studentDto.getPassword()));
        student.setAddress(studentDto.getAddress());
        student.setGender(studentDto.getGender());

        return student;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
