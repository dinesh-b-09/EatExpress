package com.example.EatExpress.repository;

import com.example.EatExpress.Enum.RestaurantCategory;
import com.example.EatExpress.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>
{
  //  Restaurant findByRestaurantCategory(RestaurantCategory restaurantCategory);
}
