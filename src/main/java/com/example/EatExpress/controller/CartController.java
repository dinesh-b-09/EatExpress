package com.example.EatExpress.controller;

import com.example.EatExpress.dto.requestDTO.FoodItemRequest;
import com.example.EatExpress.dto.responseDTO.CartResponse;
import com.example.EatExpress.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController
{

    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity addFoodItemToCart(@RequestBody FoodItemRequest foodItemRequest) {
        try
        {
            CartResponse response = cartService.addFoodItemToCart(foodItemRequest);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
