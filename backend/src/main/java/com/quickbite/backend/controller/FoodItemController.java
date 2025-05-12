package com.quickbite.backend.controller;

import com.quickbite.backend.dto.FoodItemRequestDTO;
import com.quickbite.backend.model.Category;
import com.quickbite.backend.model.FoodItem;
import com.quickbite.backend.service.CategoryService;
import com.quickbite.backend.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foodItems")
public class FoodItemController {
    private final FoodItemService foodItemService;
    private final CategoryService categoryService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService, CategoryService categoryService) {
        this.foodItemService = foodItemService;
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItemRequestDTO foodItemRequestDTO){
        System.out.println("CategoryId:"+foodItemRequestDTO.getCategoryId());
        Category category = categoryService.getCategoryById(foodItemRequestDTO.getCategoryId());
        if(category == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemRequestDTO.getName());
        foodItem.setDescription(foodItemRequestDTO.getDescription());
        foodItem.setPrice(foodItemRequestDTO.getPrice());
        foodItem.setImageUrl(foodItemRequestDTO.getImageUrl());
        foodItem.setIsAvailable(foodItemRequestDTO.getIsAvailable());
        foodItem.setCategory(category);

        FoodItem savedFoodItem = foodItemService.addFoodItem(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFoodItem);
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<List<FoodItem>> getAllItems(){
        List<FoodItem> items = foodItemService.getAllItems();
        if(items.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }
}
