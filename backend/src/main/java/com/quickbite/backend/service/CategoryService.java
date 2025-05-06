package com.quickbite.backend.service;

import com.quickbite.backend.model.Category;

public interface CategoryService {
    Category addCategory(Category category);
    Category getCategoryById(Long categoryId);
}
