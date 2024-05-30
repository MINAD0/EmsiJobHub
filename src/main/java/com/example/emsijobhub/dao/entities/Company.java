package com.example.emsijobhub.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entreprises")
public class Company extends User {
    private String name;
    private String rhName;
    private String description;
    private String webSite;
}
