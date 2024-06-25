package com.example.emsijobhub.services;

import com.example.emsijobhub.dao.entities.Company;
import com.example.emsijobhub.dao.entities.Student;
import com.example.emsijobhub.dao.repositories.CompanyRepository;
import com.example.emsijobhub.dao.repositories.UserRepository;
import com.example.emsijobhub.dto.CompanyDto;
import com.example.emsijobhub.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private UserRepository userRepository;

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company updateCompany(Long companyId, CompanyDto companyDto) {
        Company existingCompany = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));

        // Update existingStudent with fields from studentDto
        existingCompany.setName(companyDto.getName());
        existingCompany.setEmail(companyDto.getEmail());
        existingCompany.setDescription(companyDto.getDescription());
        existingCompany.setPhone(companyDto.getPhone());
        existingCompany.setAddress(companyDto.getAddress());
        existingCompany.setProfile(companyDto.getProfile());
        existingCompany.setCity(companyDto.getCity());
        existingCompany.setWebSite(companyDto.getWebSite());


        return companyRepository.save(existingCompany);
    }


}
