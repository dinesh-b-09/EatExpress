package com.example.EatExpress.repository;

import com.example.EatExpress.Enum.Gender;
import com.example.EatExpress.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{

    Optional<Customer> findByMobileNo(String mobile);

    @Query("SELECT c FROM Customer c WHERE SIZE(c.orders) = (SELECT MAX(SIZE(c1.orders)) FROM Customer c1)")
    Customer findCustomerWithMostOrders();

//    @Query("SELECT c FROM Customer c WHERE c.gender = 'female' ORDER BY SIZE(c.orders) ASC")
//    Customer findFemaleLeastOrder(Gender gender);

    @Query("SELECT c FROM Customer c WHERE c.gender = :gender ORDER BY SIZE(c.orders) ASC")
    List<Customer> findFemaleCustomerWithLeastOrders(Gender gender);

}

