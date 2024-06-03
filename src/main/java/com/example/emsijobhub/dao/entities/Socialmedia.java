package com.example.emsijobhub.dao.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "social_media")
public class Socialmedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRs;

    @Column(name = "nom_rs", nullable = false)
    private String nomRs;

    @Column(name = "url_rs", nullable = false)
    private String urlRs;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
