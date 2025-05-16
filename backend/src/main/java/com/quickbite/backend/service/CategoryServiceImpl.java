package com.quickbite.backend.service;

import com.quickbite.backend.dto.CategoryResponseDto;
import com.quickbite.backend.model.Category;
import com.quickbite.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public Category getCategoryById(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with Id: "+categoryId));
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        List<Category> categoryList= categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        for(Category category: categoryList){
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto(category.getId(), category.getName());
            categoryResponseDtos.add(categoryResponseDto);
        }
        return categoryResponseDtos;
    }
}
