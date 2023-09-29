package com.example.EatExpress.dto.responseDTO;

import com.example.EatExpress.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantResponse
{
    String name;

    String location;

    RestaurantCategory restaurantCategory;

    String contactNumber;

    boolean opened;

    List<MenuResponse> menu;
}
