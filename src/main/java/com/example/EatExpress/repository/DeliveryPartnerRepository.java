package com.example.EatExpress.repository;

import com.example.EatExpress.model.Customer;
import com.example.EatExpress.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer>
{

    String findRandomPartner = "select p from DeliveryPartner p order by RAND() LIMIT 1";

    @Query(value = findRandomPartner)
    DeliveryPartner findRandomDeliveryPartner();


    @Query("SELECT dp FROM DeliveryPartner dp WHERE SIZE(dp.orders) = (SELECT MAX(SIZE(dp1.orders)) FROM DeliveryPartner dp1)")
    DeliveryPartner findPartnerHighestDeliveries();

}
