package com.example.EatExpress.repository;

import com.example.EatExpress.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer>
{
}
