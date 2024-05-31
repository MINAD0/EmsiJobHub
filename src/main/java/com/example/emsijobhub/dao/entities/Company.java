package com.example.emsijobhub.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

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
    private String city;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModified;
}
