package com.example.EatExpress.service;

import com.example.EatExpress.Enum.FoodCategory;
import com.example.EatExpress.dto.responseDTO.CheapFoodResponse;
import com.example.EatExpress.dto.responseDTO.CostliestFoodResponse;
import com.example.EatExpress.exception.RestaurantNotFoundException;
import com.example.EatExpress.model.Menu;
import com.example.EatExpress.model.Restaurant;
import com.example.EatExpress.repository.MenuRepository;
import com.example.EatExpress.repository.RestaurantRepository;
import com.example.EatExpress.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService
{
    final MenuRepository menuRepository;

    final ValidationUtils validationUtils;

    final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, ValidationUtils validationUtils, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.validationUtils = validationUtils;
        this.restaurantRepository = restaurantRepository;
    }

    public List<String> getAllFoodsOfParticularCategory(FoodCategory category)
    {
        List<Menu> list = menuRepository.findByCategory(category);

        List<String> foodlist = new ArrayList<>();

        for(Menu menu : list)
        {
            foodlist.add(menu.getDishName());
        }
        return foodlist;

    }

    public List<String> getAllMainCoursePriceAboveX(int restaurantId, double priceX)
    {
        if(!validationUtils.validateRestaurantId(restaurantId))
        {
            throw new RestaurantNotFoundException("Invalid Restaurant Id !!");
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        List<Menu> menuList = restaurant.getAvailableMenuItems();
        List<String> itemsAbovePriceX = new ArrayList<>();

        for(Menu menu : menuList)
        {
            double itemprice = menu.getPrice();
            String s = menu.getCategory().toString();
            if((s.equals("MAIN_COURSE")) && (itemprice > priceX))
            {

                itemsAbovePriceX.add(menu.getDishName());
            }
        }
        return itemsAbovePriceX;

    }

    public List<String> getAllVegFoodsOfResto(int restoId)
    {
        // check if restoId is valid or not, if valid then get resto
        if(!validationUtils.validateRestaurantId(restoId))
        {
            throw new RestaurantNotFoundException("Invalid Restaurant Id !!");
        }
        Restaurant restaurant = restaurantRepository.findById(restoId).get();

        List<Menu> menuList = restaurant.getAvailableMenuItems();
        List<String> vegfoodlist = new ArrayList<>();

        for(Menu menu : menuList)
        {
            if(menu.isVeg() == true)
            {
                vegfoodlist.add(menu.getDishName());
            }

        }
        return vegfoodlist;
    }

    public List<String> getAllNonVegFoodsOfResto(int restoId)
    {
        // check if restoId is valid or not, if valid then get resto
        if(!validationUtils.validateRestaurantId(restoId))
        {
            throw new RestaurantNotFoundException("Invalid Restaurant Id !!");
        }
        Restaurant restaurant = restaurantRepository.findById(restoId).get();

        List<Menu> menuList = restaurant.getAvailableMenuItems();
        List<String> nonVegFoodList = new ArrayList<>();

        for(Menu menu : menuList)
        {
            if(menu.isVeg() == false)
            {
                nonVegFoodList.add(menu.getDishName());
            }
        }

        return nonVegFoodList;
    }


    public List<CheapFoodResponse> getCheapFoods(int restoId)
    {
        // check if restoId is valid or not, if valid then get resto
        if(!validationUtils.validateRestaurantId(restoId))
        {
            throw new RestaurantNotFoundException("Invalid Restaurant Id !!");
        }
        Restaurant restaurant = restaurantRepository.findById(restoId).get();

        // let the cheap price of food item be 100 or less
        List<Menu> menuList = menuRepository.findByPriceLessThanAndRestaurant_Id(100.0, restoId);
        List<CheapFoodResponse> list = new ArrayList<>();

        for(Menu menu : menuList)
        {
            CheapFoodResponse response = CheapFoodResponse.builder()
                        .dishName(menu.getDishName())
                        .price(menu.getPrice())
                        .isVeg(menu.isVeg())
                        .build();
                list.add(response);
        }
        return list;

//             List<Menu> menuList = restaurant.getAvailableMenuItems();
//        List<CheapFoodItemResponse> list = new ArrayList<>();
//        // let the maximum cheap price of food item be 100
//        for(Menu menu : menuList)
//        {
//            if(menu.getPrice() <= 100)
//            {
//                CheapFoodItemResponse response = CheapFoodItemResponse.builder()
//                        .dishName(menu.getDishName())
//                        .price(menu.getPrice())
//                        .isVeg(menu.isVeg())
//                        .build();
//                list.add(response);
//            }
//        }
//        return list;

    }


    public List<CostliestFoodResponse> getCostliestFoods(int restoId)
    {
        if(!validationUtils.validateRestaurantId(restoId))
        {
            throw new RestaurantNotFoundException("Invalid Restaurant Id !!");
        }
        Restaurant restaurant = restaurantRepository.findById(restoId).get();

        // let the costliest price of food item be 120
        List<Menu> menuList = menuRepository.findByPriceGreaterThanAndRestaurant_Id(120.0, restoId);
        List<CostliestFoodResponse> list = new ArrayList<>();

        for(Menu menu : menuList)
        {
            CostliestFoodResponse response = CostliestFoodResponse.builder()
                    .dishName(menu.getDishName())
                    .price(menu.getPrice())
                    .isVeg(menu.isVeg())
                    .build();
            list.add(response);
        }
        return list;
    }


    public List<CostliestFoodResponse> getCostliestFoodsCategory(int restoId, FoodCategory category)
    {
        if(!validationUtils.validateRestaurantId(restoId))
        {
            throw new RestaurantNotFoundException("Invalid Restaurant Id !!");
        }
        Restaurant restaurant = restaurantRepository.findById(restoId).get();

        List<Menu> menuList = menuRepository.findByPriceGreaterThanAndCategoryAndRestaurant_Id(120.0, restoId, category);
        List<CostliestFoodResponse> list = new ArrayList<>();

        for(Menu menu : menuList)
        {
            CostliestFoodResponse response = CostliestFoodResponse.builder()
                    .dishName(menu.getDishName())
                    .price(menu.getPrice())
                    .isVeg(menu.isVeg())
                    .build();
            list.add(response);
        }
        return list;

    }

}