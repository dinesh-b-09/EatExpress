package com.example.EatExpress.controller;

import com.example.EatExpress.Enum.FoodCategory;
import com.example.EatExpress.dto.responseDTO.CheapFoodResponse;
import com.example.EatExpress.dto.responseDTO.CostliestFoodResponse;
import com.example.EatExpress.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController
{

    final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    // get all foods of a particualr category
    @GetMapping("/foods/particular/category")
    public ResponseEntity getAllFoodsofParticularCategory(@RequestParam("foodcategory") FoodCategory category)
    {
        List<String> response = menuService.getAllFoodsOfParticularCategory(category);
        return new ResponseEntity(response, HttpStatus.FOUND);
    }


    // get all MAIN_COURSE items with price above x rupees from a particular restaurant.
    @GetMapping("/maincourse/items/price/above/x")
    public ResponseEntity getAllMainCoursePriceAboveX(@RequestParam("restoId") int restaurantId, @RequestParam("price") double price)
    {
        try
        {
            List<String> response = menuService.getAllMainCoursePriceAboveX(restaurantId, price);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    // get all veg foods of a restaurant
    @GetMapping("/veg/foods/{id}")
    public ResponseEntity getAllVegFoodsOfResto(@PathVariable("id") int restoId)
    {
        try
        {
            List<String> response = menuService.getAllVegFoodsOfResto(restoId);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // get all non veg foods of a restaurant
    @GetMapping("/nonveg/foods/{id}")
    public ResponseEntity getAllNonVegFoodsOfResto(@PathVariable("id") int restoId)
    {
        try
        {
            List<String> response = menuService.getAllNonVegFoodsOfResto(restoId);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    // Get cheapest 5 food items of a partiuclar restaurant
    @GetMapping("/cheap/foods/{id}")
    public ResponseEntity getCheapFoods(@PathVariable("id") int restoId)
    {

        try
        {
            List<CheapFoodResponse> response = menuService.getCheapFoods(restoId);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // Get costliest 5 food items of a partiuclar restaurant
    @GetMapping("/costliest/foods/{id}")
    public ResponseEntity getCostliestFoods(@PathVariable("id") int restoId)
    {

        try
        {
            List<CostliestFoodResponse> response = menuService.getCostliestFoods(restoId);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // Get costliest 5 food items of a partiuclar catgeory -> name of dish and rest which serves that dish
    @GetMapping("/costliest/foods/catgory")
    public ResponseEntity getCostliestFoodsCategory(@RequestParam("id") int restoId,
                                            @RequestParam("catgry") FoodCategory category)
    {
        try
        {
            List<CostliestFoodResponse> response = menuService.getCostliestFoodsCategory(restoId, category);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
