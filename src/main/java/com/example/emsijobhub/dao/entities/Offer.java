package com.example.emsijobhub.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offre")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Temporal(TemporalType.DATE)
    private Date dateOff;
    private String period;
    private Double Salary;
    private String descriptionOff;
    private String city;

    @ManyToOne
    @JoinColumn(name = "categorie", nullable = false)
    private Category category;

}
