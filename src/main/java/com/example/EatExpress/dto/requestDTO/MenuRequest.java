package com.example.EatExpress.dto.requestDTO;

import com.example.EatExpress.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuRequest
{

    int restoId;

    String dishName;

    double price;

    FoodCategory category;

    boolean veg;

    boolean available;

}
