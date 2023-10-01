package com.example.EatExpress.utils;

import com.example.EatExpress.Enum.FoodCategory;
import com.example.EatExpress.Enum.RestaurantCategory;
import com.example.EatExpress.model.Customer;
import com.example.EatExpress.model.Menu;
import com.example.EatExpress.model.Restaurant;
import com.example.EatExpress.repository.CustomerRepository;
import com.example.EatExpress.repository.MenuRepository;
import com.example.EatExpress.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils
{
    final RestaurantRepository restaurantRespository;
    final CustomerRepository customerRepository;

    final MenuRepository menuRepository;

    @Autowired
    public ValidationUtils(RestaurantRepository restaurantRespository, CustomerRepository customerRepository, MenuRepository menuRepository) {
        this.restaurantRespository = restaurantRespository;
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
    }

    public boolean validateRestaurantId(int id)
    {
        Optional<Restaurant> optionalRestaurant = restaurantRespository.findById(id);
        return optionalRestaurant.isPresent();
    }

//    public boolean validateCustomerId(int id)
//    {
//        Optional<Customer> optionalCustomer = customerRepository.findById(id);
//        return optionalCustomer.isPresent();
//    }

    public boolean validateCustomerMobile(String mobile)
    {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNo(mobile);
        return optionalCustomer.isPresent();
    }

    public boolean validateMenuId(int id)
    {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        return optionalMenu.isPresent();
    }

}
