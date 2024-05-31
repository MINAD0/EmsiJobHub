package com.example.emsijobhub.dao.repositories;


import com.example.emsijobhub.dao.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByTitre(String titre);
    List<Offer> findByCategoryId(Long categoryId);
    List<Offer> findByCity(String city);
    List<Offer> findByPeriod(String period);
    List<Offer> findBySalaryBetween(Double minSalary, Double maxSalary);
}