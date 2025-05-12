package com.quickbite.backend.service;

import com.quickbite.backend.model.FoodItem;

import java.util.List;

public interface FoodItemService {

    FoodItem addFoodItem(FoodItem foodItem);

    List<FoodItem> getAllItems();
}
