package com.example.EatExpress.dto.requestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FoodItemRequest
{
    int requiredQuantity;

    String customerMobile;

    int menuId;

}
