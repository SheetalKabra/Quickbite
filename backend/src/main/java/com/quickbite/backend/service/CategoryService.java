package com.quickbite.backend.service;

import com.quickbite.backend.dto.CategoryResponseDto;
import com.quickbite.backend.model.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category getCategoryById(Long categoryId);
    List<CategoryResponseDto> getAll();
}
