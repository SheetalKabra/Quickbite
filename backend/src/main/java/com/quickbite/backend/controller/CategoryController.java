package com.quickbite.backend.controller;

import com.quickbite.backend.model.Category;
import com.quickbite.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category savedCategory = categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

}
