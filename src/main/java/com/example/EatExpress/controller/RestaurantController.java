package com.example.EatExpress.controller;

import com.example.EatExpress.dto.requestDTO.MenuRequest;
import com.example.EatExpress.dto.requestDTO.RestaurantRequest;
import com.example.EatExpress.dto.responseDTO.MenuResponse;
import com.example.EatExpress.dto.responseDTO.RestaurantResponse;
import com.example.EatExpress.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add/menu")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuRequest menuRequest)
    {
        try
        {
            RestaurantResponse response = restaurantService.addMenuItemToRestaurant(menuRequest);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    // get menu of a restaurant
    @GetMapping("/menu/restaurant")
    public ResponseEntity getMenuOfARestauarant(@RequestParam("id") int restaurantId)
    {
        try
        {
            List<MenuResponse> response = restaurantService.getMenuOfARestauarant(restaurantId);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    // give all the restaurants who have served more than 'x' number of orders
    @GetMapping("/all/restos/served/orders/{x}")
    public ResponseEntity getAllRestaurantsWhoServedXOrders(@PathVariable("x") int x)
    {
        List<RestaurantResponse> responseList = restaurantService.getAllRestaurantsWhoServedXOrders(x);

        if(responseList.isEmpty())
        {
            return new ResponseEntity("No Restaurant served more than "+x+" number of orders", HttpStatus.OK);
        }
        return new ResponseEntity(responseList, HttpStatus.FOUND);
    }


    // give the restaurants which have maximum number of items in their menu and which are opened also
    @GetMapping("/all/restos/max/items/opened")
    public ResponseEntity getAllRestaurantsWithMaxItemsAndOpened()
    {
        List<RestaurantResponse> responseList = restaurantService.getAllRestaurantsWithMaxItemsAndOpened();

        if(responseList.isEmpty())
        {
            return new ResponseEntity("No Restaurant has food items in their menu", HttpStatus.OK);
        }

        return new ResponseEntity(responseList, HttpStatus.FOUND);
    }

}
