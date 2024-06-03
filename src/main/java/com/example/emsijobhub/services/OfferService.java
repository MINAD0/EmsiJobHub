package com.example.emsijobhub.services;

import com.example.emsijobhub.dao.entities.Category;
import com.example.emsijobhub.dao.entities.Offer;
import com.example.emsijobhub.dao.repositories.CategoryRepository;
import com.example.emsijobhub.dao.repositories.OfferRepository;
import com.example.emsijobhub.dto.CategoryDto;
import com.example.emsijobhub.dto.OfferDto;
import com.example.emsijobhub.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public OfferDto createOffer(OfferDto offerDto) {
        Category category = categoryRepository.findById(offerDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Offer offer = Offer.builder()
                .titre(offerDto.getTitre())
                .dateOff(offerDto.getDateOff())
                .period(offerDto.getPeriod())
                .salary(offerDto.getSalary())
                .descriptionOff(offerDto.getDescriptionOff())
                .city(offerDto.getCity())
                .category(category)
                .build();

        Offer savedOffer = offerRepository.save(offer);
        return mapToDto(savedOffer);
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public OfferDto getOfferById(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
        return mapToDto(offer);
    }

    public OfferDto updateOffer(Long id, OfferDto offerDto) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found"));

        Category category = categoryRepository.findById(offerDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        offer.setTitre(offerDto.getTitre());
        offer.setDateOff(offerDto.getDateOff());
        offer.setPeriod(offerDto.getPeriod());
        offer.setSalary(offerDto.getSalary());
        offer.setDescriptionOff(offerDto.getDescriptionOff());
        offer.setCity(offerDto.getCity());
        offer.setCategory(category);

        Offer updatedOffer = offerRepository.save(offer);
        return mapToDto(updatedOffer);
    }

    public void deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
        offerRepository.delete(offer);
    }

    private OfferDto mapToDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .titre(offer.getTitre())
                .dateOff(offer.getDateOff())
                .period(offer.getPeriod())
                .salary(offer.getSalary())
                .descriptionOff(offer.getDescriptionOff())
                .city(offer.getCity())
                .categoryId(offer.getCategory().getId())
                .build();
    }

    // Méthodes pour Category

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .descriptionCat(categoryDto.getDescriptionCat())
                .build();

        Category savedCategory = categoryRepository.save(category);
        return mapToCategoryDto(savedCategory);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return mapToCategoryDto(category);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setName(categoryDto.getName());
        category.setDescriptionCat(categoryDto.getDescriptionCat());

        Category updatedCategory = categoryRepository.save(category);
        return mapToCategoryDto(updatedCategory);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }

    private CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .descriptionCat(category.getDescriptionCat())
                .build();
    }
}
