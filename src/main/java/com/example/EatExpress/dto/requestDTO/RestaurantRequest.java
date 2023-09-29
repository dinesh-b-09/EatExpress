package com.example.EatExpress.dto.requestDTO;

import com.example.EatExpress.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantRequest
{
    String name;

    String location;

    RestaurantCategory restaurantCategory;

    String contactNumber;

}
