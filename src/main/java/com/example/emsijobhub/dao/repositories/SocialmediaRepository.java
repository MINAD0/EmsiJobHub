package com.example.emsijobhub.dao.repositories;


import com.example.emsijobhub.dao.entities.Socialmedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialmediaRepository extends JpaRepository<Socialmedia, Long> {

    List<Socialmedia> findByNomRs(String nomRs);

    List<Socialmedia> findByUrlRs(String urlRs);

}