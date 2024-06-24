package com.example.emsijobhub.services;

import com.example.emsijobhub.dao.entities.Socialmedia;
import com.example.emsijobhub.dao.entities.User;
import com.example.emsijobhub.dao.repositories.SocialmediaRepository;
import com.example.emsijobhub.dao.repositories.UserRepository;
import com.example.emsijobhub.dto.SocialmediaDto;
import com.example.emsijobhub.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialmediaService {

    @Autowired
    private SocialmediaRepository socialmediaRepository;

    @Autowired
    private UserRepository userRepository;

    public SocialmediaDto createSocialmedia(SocialmediaDto socialmediaDto) {
        User user = userRepository.findById(socialmediaDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Socialmedia socialmedia = Socialmedia.builder()
                .nomRs(socialmediaDto.getNomRs())
                .urlRs(socialmediaDto.getUrlRs())
                .user(user)
                .build();

        Socialmedia savedSocialmedia = socialmediaRepository.save(socialmedia);
        return mapToDto(savedSocialmedia);
    }

    public List<SocialmediaDto> getAllSocialmedias() {
        return socialmediaRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public SocialmediaDto getSocialmediaById(Long idRs) {
        Socialmedia socialmedia = socialmediaRepository.findById(idRs)
                .orElseThrow(() -> new ResourceNotFoundException("Social media not found"));
        return mapToDto(socialmedia);
    }

    public SocialmediaDto updateSocialmedia(Long idRs, SocialmediaDto socialmediaDto) {
        Socialmedia socialmedia = socialmediaRepository.findById(idRs)
                .orElseThrow(() -> new ResourceNotFoundException("Social media not found"));

        User user = userRepository.findById(socialmediaDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        socialmedia.setNomRs(socialmediaDto.getNomRs());
        socialmedia.setUrlRs(socialmediaDto.getUrlRs());
        socialmedia.setUser(user);

        Socialmedia updatedSocialmedia = socialmediaRepository.save(socialmedia);
        return mapToDto(updatedSocialmedia);
    }

    public void deleteSocialmedia(Long idRs) {
        Socialmedia socialmedia = socialmediaRepository.findById(idRs)
                .orElseThrow(() -> new ResourceNotFoundException("Social media not found"));
        socialmediaRepository.delete(socialmedia);
    }

    private SocialmediaDto mapToDto(Socialmedia socialmedia) {
        return SocialmediaDto.builder()
                .idRs(socialmedia.getIdRs())
                .nomRs(socialmedia.getNomRs())
                .urlRs(socialmedia.getUrlRs())
                .userId(socialmedia.getUser().getId())
                .build();
    }
}
