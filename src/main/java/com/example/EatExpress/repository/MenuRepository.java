package com.example.EatExpress.repository;

import com.example.EatExpress.Enum.FoodCategory;
import com.example.EatExpress.model.FoodItem;
import com.example.EatExpress.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>
{
//    List<Menu> getAllFoodsOfParticularCategory(FoodCategory foodCategory);

    public List<Menu> findByCategory(FoodCategory category);

    List<Menu> findByPriceLessThanAndRestaurant_Id(double price, int id);

    List<Menu> findByPriceGreaterThanAndRestaurant_Id(double price, int id);

    List<Menu> findByPriceGreaterThanAndCategoryAndRestaurant_Id(double price, int id, FoodCategory category);
}
