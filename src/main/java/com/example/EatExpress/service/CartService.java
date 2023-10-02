package com.example.EatExpress.service;

import com.example.EatExpress.dto.requestDTO.FoodItemRequest;
import com.example.EatExpress.dto.responseDTO.CartResponse;
import com.example.EatExpress.dto.responseDTO.FoodItemResponse;
import com.example.EatExpress.exception.CustomerNotFoundException;
import com.example.EatExpress.exception.MenuNotFoundException;
import com.example.EatExpress.model.*;
import com.example.EatExpress.repository.CartRepository;
import com.example.EatExpress.repository.CustomerRepository;
import com.example.EatExpress.repository.FoodItemRepository;
import com.example.EatExpress.repository.MenuRepository;
import com.example.EatExpress.transformer.CartTransformer;
import com.example.EatExpress.transformer.FoodItemTransformer;
import com.example.EatExpress.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService
{
    final CustomerRepository customerRepository;

    final ValidationUtils validationUtils;

    final MenuRepository menuRepository;

    final FoodItemRepository foodItemRepository;

    final CartRepository cartRepository;

    @Autowired
    public CartService(CustomerRepository customerRepository, ValidationUtils validationUtils, MenuRepository menuRepository, FoodItemRepository foodItemRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.validationUtils = validationUtils;
        this.menuRepository = menuRepository;
        this.foodItemRepository = foodItemRepository;
        this.cartRepository = cartRepository;
    }

    public CartResponse addFoodItemToCart(FoodItemRequest foodItemRequest)
    {
        // Optional<Customer> customer = customerRepository.findByMobileNo(foodItemRequest.getCustomerMobile());

        //  check whether customer mobileNumber is valid or not, if valid then get customer
        if(!validationUtils.validateCustomerMobile(foodItemRequest.getCustomerMobile()))
        {
            throw new CustomerNotFoundException("Customer doesn't exist!!");
        }
        Customer customer = customerRepository.findByMobileNo(foodItemRequest.getCustomerMobile()).get();

        // check menu id is valid or not, if valid then get menu
        if(!validationUtils.validateMenuId(foodItemRequest.getMenuId()))
        {
            throw new MenuNotFoundException("Menu Item not available in the restaurant!!!");
        }
        Menu menu = menuRepository.findById(foodItemRequest.getMenuId()).get();

        //check if restaurant is opened or not
        if(!menu.getRestaurant().isOpened() || !menu.isAvailable()) {
            throw new MenuNotFoundException("Given dish is out of stock for now!!!");
        }

        // get cart
        Cart cart = customer.getCart();

        // having item from same restaurant
        if(cart.getFoodItems().size()!=0)
        {
            Restaurant currRestaurant = cart.getFoodItems().get(0).getMenu().getRestaurant();
            Restaurant newRestaurant = menu.getRestaurant();

            if(!currRestaurant.equals(newRestaurant))
            {
                List<FoodItem> foodItems = cart.getFoodItems();
                for(FoodItem foodItem: foodItems)
                {
                    foodItem.setCart(null);
                    foodItem.setOrder(null);
                    foodItem.setMenu(null);
                }
                cart.setCartTotal(0);
                cart.getFoodItems().clear();
                foodItemRepository.deleteAll(foodItems);
            }
        }

        boolean alreadyExists = false;
        FoodItem savedFoodItem = null;
        if(cart.getFoodItems().size()!=0)
        {
            for(FoodItem foodItem: cart.getFoodItems())
            {
                if(foodItem.getMenu().getId()==menu.getId()){
                    savedFoodItem = foodItem;
                    int curr = foodItem.getRequiredQuantity();
                    foodItem.setRequiredQuantity(curr+foodItemRequest.getRequiredQuantity());
                    alreadyExists = true;
                    break;
                }
            }
        }

        if(!alreadyExists)
        {
            FoodItem foodItem = FoodItem.builder()
                    .menu(menu)
                    .requiredQuantity(foodItemRequest.getRequiredQuantity())
                    .totalCost(foodItemRequest.getRequiredQuantity() * menu.getPrice())
                    .build();

            savedFoodItem = foodItemRepository.save(foodItem);
            cart.getFoodItems().add(savedFoodItem);
            menu.getFoodItems().add(savedFoodItem);
            savedFoodItem.setCart(cart);
        }

        double cartTotal = 0;
        for(FoodItem food: cart.getFoodItems())
        {
            cartTotal += food.getRequiredQuantity() * food.getMenu().getPrice();
        }

        cart.setCartTotal(cartTotal);
        // save
        Cart savedCart = cartRepository.save(cart);
        Menu savedMenuItem = menuRepository.save(menu);

        // prepare
        List<FoodItemResponse> foodResponseList = new ArrayList<>();
        for(FoodItem food: cart.getFoodItems()){
            foodResponseList.add(FoodItemTransformer.FoodItemToFoodItemResponse(food));
        }

        // model --> responseDto
        return CartTransformer.CartToCartResponse(savedCart,foodResponseList,savedMenuItem,cartTotal);

    }


}
