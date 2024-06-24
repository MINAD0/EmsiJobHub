package com.example.emsijobhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    private Long id;
    private String titre;
    private Date dateOff;
    private String period;
    private Double salary;
    private String descriptionOff;
    private String city;
    private Long categoryId;  // ID de la catégorie associée
}
