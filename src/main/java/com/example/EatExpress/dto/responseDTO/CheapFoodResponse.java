package com.example.EatExpress.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CheapFoodResponse
{
    String dishName;

    double price;

    boolean isVeg;
}
