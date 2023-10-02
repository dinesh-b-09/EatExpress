package com.example.EatExpress.service;

import com.example.EatExpress.dto.responseDTO.OrderResponse;
import com.example.EatExpress.exception.CustomerNotFoundException;
import com.example.EatExpress.exception.EmptyCartException;
import com.example.EatExpress.model.*;
import com.example.EatExpress.repository.CustomerRepository;
import com.example.EatExpress.repository.DeliveryPartnerRepository;
import com.example.EatExpress.repository.OrderEntityRepository;
import com.example.EatExpress.repository.RestaurantRepository;
import com.example.EatExpress.transformer.OrderTransformer;
import com.example.EatExpress.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService
{
    final CustomerRepository customerRepository;
    final OrderEntityRepository orderEntityRepository;

    final DeliveryPartnerRepository deliveryPartnerRepository;

    final ValidationUtils validationUtils;

    final RestaurantRepository restaurantRespository;

    @Autowired
    public OrderService(CustomerRepository customerRepository,
                        OrderEntityRepository orderEntityRepository,
                        DeliveryPartnerRepository deliveryPartnerRepository,
                        ValidationUtils validationUtils, RestaurantRepository restaurantRespository) {
        this.customerRepository = customerRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.validationUtils = validationUtils;
        this.restaurantRespository = restaurantRespository;
    }

    public OrderResponse placeOrder(String customerMobile) {

        // verify the customer
//        Customer customer = customerRepository.findByMobileNo(customerMobile);
//        if(customer == null){
//            throw new CustomerNotFoundException("Invalid mobile number!!!");
//        }
        if(!validationUtils.validateCustomerMobile(customerMobile))
        {
            throw new CustomerNotFoundException("Customer doesn't exist!!");
        }
        Customer customer = customerRepository.findByMobileNo(customerMobile).get();

        // verify if cart is empty or not
        Cart cart = customer.getCart();
        if(cart.getFoodItems().size()==0){
            throw new EmptyCartException("Sorry! Your cart is empty!!!");
        }

        // find a delivery partner to deliver. Randomly
        DeliveryPartner partner = deliveryPartnerRepository.findRandomDeliveryPartner();
        Restaurant restaurant = cart.getFoodItems().get(0).getMenu().getRestaurant();

        // prepare the order entity
        OrderEntity order = OrderTransformer.prepareOrderEntity(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);

        order.setCustomer(customer);
        order.setDeliveryPartner(partner);
        order.setRestaurant(restaurant);
        order.setFoodItems(cart.getFoodItems());

        customer.getOrders().add(savedOrder);
        partner.getOrders().add(savedOrder);
        restaurant.getOrders().add(savedOrder);

        for(FoodItem foodItem: cart.getFoodItems()){
            foodItem.setCart(null);
            foodItem.setOrder(savedOrder);
        }
        clearCart(cart);

        customerRepository.save(customer);
        deliveryPartnerRepository.save(partner);
        restaurantRespository.save(restaurant);

        // prepare orderresponse
        return OrderTransformer.OrderToOrderResponse(savedOrder);
    }

    private void clearCart(Cart cart) {
        cart.setCartTotal(0);
        cart.setFoodItems(new ArrayList<>());
    }
}
