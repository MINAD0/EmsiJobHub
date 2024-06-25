package com.example.emsijobhub.web;

import com.example.emsijobhub.dao.entities.Student;
import com.example.emsijobhub.dao.entities.User;
import com.example.emsijobhub.dto.StudentDto;
import com.example.emsijobhub.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

//    @GetMapping("/{email}")
//    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
//        Optional<Student> student = studentService.getStudentByEmail(email);
//        return student.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Student updatedStudent = studentService.updateStudent(id, studentDto);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
}
