package com.example.EatExpress.controller;

import com.example.EatExpress.dto.requestDTO.MenuRequest;
import com.example.EatExpress.dto.requestDTO.RestaurantRequest;
import com.example.EatExpress.dto.responseDTO.RestaurantResponse;
import com.example.EatExpress.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController
{

    final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest)
    {
        try
        {
            RestaurantResponse response = restaurantService.addRestaurant(restaurantRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity("Input format unmatched!", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/opened/status")
    public ResponseEntity changeOpenedStatus(@RequestParam("id") int id)
    {
        try
        {
            RestaurantResponse response = restaurantService.changeOpenedStatus(id);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add/menu")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuRequest menuRequest)
    {
        RestaurantResponse response = restaurantService.addMenuItemToRestaurant(menuRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    // get menu of a restaurant

    // give all the restauratns who have served more than 'x' number of orders

    // give the restaurants which have maximum number of items in their menu and which are opened also

}
