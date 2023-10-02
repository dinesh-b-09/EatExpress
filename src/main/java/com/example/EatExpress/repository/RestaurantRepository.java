package com.example.EatExpress.repository;

import com.example.EatExpress.Enum.RestaurantCategory;
import com.example.EatExpress.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>
{

  //  Restaurant findByRestaurantCategory(RestaurantCategory restaurantCategory);

    @Query(value = "Select r from Restaurant r where size(r.orders) >= :x")
    List<Restaurant> findByOrder(int x);


    @Query(value = "Select r from Restaurant r where size(r.availableMenuItems) >= :max")
    List<Restaurant> findByMenu(int max);


    List<Restaurant> findByOpened(boolean opened);



}
