package com.example.emsijobhub.services;

import com.example.emsijobhub.auth.AuthenticationRequest;
import com.example.emsijobhub.auth.AuthenticationResponse;
import com.example.emsijobhub.auth.CompanyRegisterRequest;
import com.example.emsijobhub.auth.StudentRegisterRequest;
import com.example.emsijobhub.config.JwtService;
import com.example.emsijobhub.dao.entities.Company;
import com.example.emsijobhub.dao.entities.Role;
import com.example.emsijobhub.dao.entities.Student;
import com.example.emsijobhub.dao.entities.User;
import com.example.emsijobhub.dao.repositories.UserRepository;
import com.example.emsijobhub.dto.StudentDto;
import com.example.emsijobhub.web.AuthenticationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;


    private final String PROFILE_UPLOAD_DIR = "uploads/profiles/";
    private final String RESUME_UPLOAD_DIR = "uploads/resumes/";

    public AuthenticationResponse registerStudent(StudentRegisterRequest studentRegisterRequest) throws IOException {
        Student student = new Student();
        student.setName(studentRegisterRequest.getName());
        student.setEmail(studentRegisterRequest.getEmail());
        student.setRegistrationNb(studentRegisterRequest.getRegistrationNb());
        student.setPasswd(passwordEncoder.encode(studentRegisterRequest.getPassword()));
        student.setPhone(studentRegisterRequest.getPhone());
        student.setBirth(studentRegisterRequest.getBirth());
        student.setDescription(studentRegisterRequest.getDescription());
        student.setBio(studentRegisterRequest.getBio());
        student.setAddress(studentRegisterRequest.getAddress());
        student.setGender(studentRegisterRequest.getGender());
        String profilePath = saveBase64File(studentRegisterRequest.getProfile(), studentRegisterRequest.getName() + "-profile.png", PROFILE_UPLOAD_DIR);
        String resumePath = saveBase64File(studentRegisterRequest.getResume(), studentRegisterRequest.getName() + "-resume.pdf", RESUME_UPLOAD_DIR);
        student.setProfile(profilePath);
        student.setResume(resumePath);
        student.setRole(Role.STUDENT);

       userRepository.save(student);
       UserDetails userDetails = loadUserByUsername(student.getEmail());
        var jwtToken = jwtService.generateToken(new HashMap<>(), userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerCompany(CompanyRegisterRequest companyRegisterRequest) throws IOException {
        Company company = new Company();
        company.setName(companyRegisterRequest.getName());
        company.setEmail(companyRegisterRequest.getEmail());
        company.setPasswd(passwordEncoder.encode(companyRegisterRequest.getPassword()));
        company.setPhone(companyRegisterRequest.getPhone());
        company.setAddress(companyRegisterRequest.getAddress());
        String profilePath = saveBase64File(companyRegisterRequest.getProfile(), companyRegisterRequest.getName() + "-profile.png", PROFILE_UPLOAD_DIR);
        company.setProfile(profilePath);
        company.setRole(Role.COMPANY);

        userRepository.save(company);
        UserDetails userDetails = loadUserByUsername(company.getEmail());
        var jwtToken = jwtService.generateToken(new HashMap<>(), userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private String saveBase64File(String base64File, String fileName, String uploadDir) throws IOException {
        if (base64File == null || base64File.isEmpty()) {
            return null;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(base64File);
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Create directories if they do not exist
        }
        String filePath = uploadDir + fileName;
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(decodedBytes);
        }
        return filePath;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPasswd(), new ArrayList<>());
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        log.debug("Authenticating user with email: {}", authenticationRequest.getEmail());
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        // Load user details only once
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + authenticationRequest.getEmail()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPasswd(), new ArrayList<>());

        var jwtToken = jwtService.generateToken(new HashMap<>(), userDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .userType(user.getRole().name())
                .build();
    }



}
