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
    private String nom_cand;
    private String prenom_cand;
    private String description_cand;
    private String bio_cand;
    private String titre_cand;
    private Date date_naiss_cand;
    private String genre;
    private String image_cand;

}