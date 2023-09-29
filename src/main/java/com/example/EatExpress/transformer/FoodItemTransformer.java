package com.example.EatExpress.transformer;


import com.example.EatExpress.dto.responseDTO.FoodItemResponse;
import com.example.EatExpress.model.FoodItem;


public class FoodItemTransformer
{
    public static FoodItemResponse FoodItemToFoodItemResponse(FoodItem food)
    {
        return FoodItemResponse.builder()
                .dishName(food.getMenu().getDishName())
                .price(food.getMenu().getPrice())
                .category(food.getMenu().getCategory())
                .veg(food.getMenu().isVeg())
                .quantityAdded(food.getRequiredQuantity())
                .build();
    }
}
