package com.example.emsijobhub.web;

import com.example.emsijobhub.auth.AuthenticationRequest;
import com.example.emsijobhub.auth.AuthenticationResponse;
import com.example.emsijobhub.auth.CompanyRegisterRequest;
import com.example.emsijobhub.auth.StudentRegisterRequest;
import com.example.emsijobhub.dto.CompanyDto;
import com.example.emsijobhub.dto.StudentDto;
import com.example.emsijobhub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/student/register")
    public ResponseEntity<AuthenticationResponse> StudentRegister(@RequestBody StudentRegisterRequest studentRegisterRequest) throws IOException {
        return ResponseEntity.ok(userService.registerStudent(studentRegisterRequest));
    }

    @PostMapping("/company/register")
    public ResponseEntity<AuthenticationResponse> CompanyRegister(@RequestBody CompanyRegisterRequest companyRegisterRequest) throws IOException {
        return ResponseEntity.ok(userService.registerCompany(companyRegisterRequest));
    }

    @PostMapping("/login")
    public  ResponseEntity<AuthenticationResponse> Login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(userService.login(authenticationRequest));
    }

}
