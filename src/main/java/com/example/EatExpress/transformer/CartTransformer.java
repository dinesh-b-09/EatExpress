package com.example.EatExpress.transformer;

import com.example.EatExpress.dto.responseDTO.CartResponse;
import com.example.EatExpress.dto.responseDTO.FoodItemResponse;
import com.example.EatExpress.model.Cart;
import com.example.EatExpress.model.Menu;

import java.util.List;

public class CartTransformer
{

    public static CartResponse CartToCartResponse(Cart savedCart, List<FoodItemResponse> foodResponseList,
                                                  Menu savedMenuItem, double cartTotal)
    {
        return CartResponse.builder()
                .customerName(savedCart.getCustomer().getName())
                .customerMobile(savedCart.getCustomer().getMobileNo())
                .customerAddress(savedCart.getCustomer().getAddress())
                .foodList(foodResponseList)
                .restaurantName(savedMenuItem.getRestaurant().getName())
                .cartTotal(cartTotal)
                .build();
    }
}
