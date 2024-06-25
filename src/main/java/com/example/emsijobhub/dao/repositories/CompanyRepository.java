package com.example.emsijobhub.dao.repositories;

import com.example.emsijobhub.dao.entities.Company;
import com.example.emsijobhub.dao.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByEmail(String email);
    Optional<Company> findCompanyById(Long id);
}
