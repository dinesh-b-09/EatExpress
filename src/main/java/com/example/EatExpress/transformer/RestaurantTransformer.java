package com.example.EatExpress.transformer;

import com.example.EatExpress.dto.requestDTO.RestaurantRequest;
import com.example.EatExpress.dto.responseDTO.MenuResponse;
import com.example.EatExpress.dto.responseDTO.RestaurantResponse;
import com.example.EatExpress.model.FoodItem;
import com.example.EatExpress.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer
{
    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest)
    {
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .location(restaurantRequest.getLocation())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .contactNumber(restaurantRequest.getContactNumber())
                .opened(true)
                .availableMenuItems(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
    }

    public static RestaurantResponse RestaurantToRestuarantResponse(Restaurant restaurant)
    {

        List<MenuResponse> menu = restaurant.getAvailableMenuItems()
                .stream()
                .map(foodItem -> MenuTransformer.MenuToMenuResponse(foodItem))
                .collect(Collectors.toList());


        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .restaurantCategory(restaurant.getRestaurantCategory())
                .contactNumber(restaurant.getContactNumber())
                .opened(restaurant.isOpened())
                .menu(menu)
                .build();
    }
}
