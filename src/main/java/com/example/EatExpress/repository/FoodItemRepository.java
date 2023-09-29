package com.example.EatExpress.repository;

import com.example.EatExpress.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer>
{
}
