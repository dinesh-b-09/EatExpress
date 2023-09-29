package com.example.EatExpress.transformer;

import com.example.EatExpress.dto.requestDTO.CustomerRequest;
import com.example.EatExpress.dto.responseDTO.CartResponse;
import com.example.EatExpress.dto.responseDTO.CustomerResponse;
import com.example.EatExpress.model.Customer;

import java.util.ArrayList;

public class CustomerTransformer
{
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest)
    {
        return Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .email(customerRequest.getEmail())
                .mobileNo(customerRequest.getMobileNo())
                .gender(customerRequest.getGender())
                .build();
    }
     public static CustomerResponse CustomerToCustomerResponse(Customer customer)
     {
    //     CartResponse cartResponse = CartTransformer.CartToCartResponse(customer.getCart());

         return CustomerResponse.builder()
                 .name(customer.getName())
                 .email(customer.getEmail())
                 .address(customer.getAddress())
                 .mobileNo(customer.getMobileNo())
                 .gender(customer.getGender())
                 .cartTotal(customer.getCart().getCartTotal())
                 .foodItems(new ArrayList<>())
                 .build();
     }
}
