package com.example.emsijobhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialmediaDto {
    private Long idRs;
    private String nomRs;
    private String urlRs;
    private Long userId;  // ID de l'utilisateur associé
}
