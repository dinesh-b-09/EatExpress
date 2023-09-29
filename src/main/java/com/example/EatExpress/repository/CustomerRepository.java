package com.example.EatExpress.repository;

import com.example.EatExpress.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{

    Optional<Customer> findByMobileNo(String mobile);

}
