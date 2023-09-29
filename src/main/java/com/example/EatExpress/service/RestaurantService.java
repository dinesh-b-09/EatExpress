package com.example.EatExpress.service;

import com.example.EatExpress.dto.requestDTO.MenuRequest;
import com.example.EatExpress.dto.requestDTO.RestaurantRequest;
import com.example.EatExpress.dto.responseDTO.RestaurantResponse;
import com.example.EatExpress.exception.CustomerNotFoundException;
import com.example.EatExpress.exception.RestaurantNotFoundException;
import com.example.EatExpress.model.Customer;
import com.example.EatExpress.model.Menu;
import com.example.EatExpress.model.Restaurant;
import com.example.EatExpress.repository.RestaurantRepository;
import com.example.EatExpress.transformer.MenuTransformer;
import com.example.EatExpress.transformer.RestaurantTransformer;
import com.example.EatExpress.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService
{
    final RestaurantRepository restaurantRepository;

    final ValidationUtils validationUtils;

    /**
     * Constructor Injection
     *
     * @param restaurantRepository --> bean of restaurant service
     * @param validationUtils
     */

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ValidationUtils validationUtils)
    {
        this.restaurantRepository = restaurantRepository;
        this.validationUtils = validationUtils;
    }

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest)
    {
        // dto --> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        // save in repo
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // model --> response
        return RestaurantTransformer.RestaurantToRestuarantResponse(savedRestaurant);
    }


    public RestaurantResponse changeOpenedStatus(int id)
    {
//        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
//
//        if(optionalRestaurant.isEmpty())
//        {
//            throw new RestaurantNotFoundException("Invalid Resaturant id !!");
//        }
//
//        Restaurant restaurant =optionalRestaurant.get();

        // check resto id is valid or not
        if(!validationUtils.validateRestaurantId(id))
        {
            throw new RestaurantNotFoundException("Resraurant doesn't exist!!");
        }
        Restaurant restaurant = restaurantRepository.findById(id).get();

        restaurant.setOpened(!restaurant.isOpened());  // if false becomes true, vice versa

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestuarantResponse(savedRestaurant);
    }


    public RestaurantResponse addMenuItemToRestaurant(MenuRequest menuRequest)
    {
//        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(menuRequest.getRestoId());
//
//        if(optionalRestaurant.isEmpty())
//        {
//            throw new RestaurantNotFoundException("Invalid Resaturant id !!");
//        }       --> given in validation package

        //check whether resto id is valid or not
        if(!validationUtils.validateRestaurantId(menuRequest.getRestoId())){
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!");
        }

        Restaurant restaurant = restaurantRepository.findById(menuRequest.getRestoId()).get();

        // menu dto --> menu model
        Menu menu = MenuTransformer.MenuRequestToMenu(menuRequest);

        menu.setRestaurant(restaurant);
        restaurant.getAvailableMenuItems().add(menu);

        // save resto and menu
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // prepare response and return
        return RestaurantTransformer.RestaurantToRestuarantResponse(savedRestaurant);


    }
}
