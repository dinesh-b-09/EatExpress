package com.example.EatExpress.controller;


import com.example.EatExpress.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fooditem")
public class FoodItemController
{
    final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }


    // give the food category which is ordered most
    @GetMapping("/foodcategory/ordered/most")
    public ResponseEntity getFoodCategoryOrderedMost()
    {
        String response = foodItemService.getFoodCategoryOrderedMost();
        return new ResponseEntity(response, HttpStatus.FOUND);
    }







}
