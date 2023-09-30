package com.example.EatExpress.service;

import com.example.EatExpress.Enum.Gender;
import com.example.EatExpress.dto.requestDTO.CustomerRequest;
import com.example.EatExpress.dto.responseDTO.CustomerResponse;
import com.example.EatExpress.exception.CustomerNotFoundException;
import com.example.EatExpress.model.Cart;
import com.example.EatExpress.model.Customer;
import com.example.EatExpress.repository.CustomerRepository;
import com.example.EatExpress.transformer.CustomerTransformer;
import com.example.EatExpress.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerService
{

//    @Autowired
//    CustomerRepository customerRepository;

    final CustomerRepository customerRepository;

    final ValidationUtils validationUtils;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ValidationUtils validationUtils) {
        this.customerRepository = customerRepository;
        this.validationUtils = validationUtils;
    }


    public CustomerResponse addCustomer(CustomerRequest customerRequest)
    {

        //dto - customerRequest to Customer
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        //allocate cart to customer
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        // set this cart to customer
        customer.setCart(cart);

        //save customer in repo
        Customer savedCustomer = customerRepository.save(customer); // saves both customer and cart

        // customer to customerResponse
        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);

    }

    public CustomerResponse getCustomerByMobile(String mobile)
    {
//        Optional<Customer> optionalCustomer = customerRepository.findByMobileNo(mobile);
//
//        if(optionalCustomer.isEmpty())
//        {
//            throw new CustomerNotFoundException("Wrong Mobile_Number");
//        }

        // check if mobile valid or not
        if(!validationUtils.validateCustomerMobile(mobile))
        {
            throw new CustomerNotFoundException("Customer doesn't exist!!");
        }
        Customer customer = customerRepository.findByMobileNo(mobile).get();

        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }

    public String getCustomerWithMaxOrders()
    {
//        List<Customer> customerList = customerRepository.findAll();
//        int max = 0;
//        String customerWithMaxOrders="";
//        for(Customer customer : customerList)
//        {
//            int count = customer.getOrders().size();
//
//            if(count > max)
//            {
//                max= count;
//                customerWithMaxOrders = customer.getName();
//            }
//        }
//        System.out.println(max);
//        return customerWithMaxOrders;

        Customer customer = customerRepository.findCustomerWithMostOrders();

        if(customer != null)
        {
            return customer.getName();
        }

        return "customers didn't order or customers may have equal number of orders";
    }

    public String getFemaleWithLeastOrders()
    {

        List<Customer> femaleCustomers = customerRepository.findFemaleCustomerWithLeastOrders(Gender.FEMALE);

        if (!femaleCustomers.isEmpty())
        {
            return femaleCustomers.get(0).getName(); // first female customer has the least orders
        }

        return "No female customers didn't order or customers may have equal number of orders"; // No female customers found
    }
}
