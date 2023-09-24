package com.example.EatExpress.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartResponse
{
    int cartTotal;


    List<FoodResponse> foodItems;
}
