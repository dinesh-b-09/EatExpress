package com.example.EatExpress.dto.responseDTO;

import com.example.EatExpress.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FoodItemResponse
{
    String dishName;

    double price;

    FoodCategory category;

    boolean veg;

    int quantityAdded;

}
