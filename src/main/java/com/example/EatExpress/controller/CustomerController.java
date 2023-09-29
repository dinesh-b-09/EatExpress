package com.example.EatExpress.controller;

import com.example.EatExpress.dto.requestDTO.CustomerRequest;
import com.example.EatExpress.dto.responseDTO.CustomerResponse;
import com.example.EatExpress.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
//    @Autowired
//    CustomerService customerService;   --> field Injection



    final CustomerService customerService;  //  constructor Injection  ----> Always use in enterprise applications
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }



    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest)
    {
        //if we dont throw exception and give some field mismatched then in DB the id gets incremented
        try
        {
            CustomerResponse response = customerService.addCustomer(customerRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity("Input format unmatched!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/customer")
    public ResponseEntity getCustomerByMobile(@RequestParam("mobile") String mobile)
    {
        try
        {
            CustomerResponse response = customerService.getCustomerByMobile(mobile);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // get the customer with most number of orders

    // get the female customer with least number of orders



}
