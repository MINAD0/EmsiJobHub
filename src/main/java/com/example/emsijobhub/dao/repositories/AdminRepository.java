package com.example.emsijobhub.dao.repositories;

import com.example.emsijobhub.dao.entities.Admin_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin_table, Long> {
}
