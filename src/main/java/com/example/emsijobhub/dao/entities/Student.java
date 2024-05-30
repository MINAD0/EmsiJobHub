package com.example.emsijobhub.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidats")
public class Student extends User {
    private String firstName;
    private String lastName;
    private String description;
    private String bio;
    private String title;
    private Date birth;
    private String sexe;
}