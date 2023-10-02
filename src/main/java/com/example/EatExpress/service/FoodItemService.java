package com.example.EatExpress.service;

import com.example.EatExpress.model.FoodItem;
import com.example.EatExpress.model.OrderEntity;
import com.example.EatExpress.repository.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodItemService
{
    final OrderEntityRepository orderEntityRepository;

    @Autowired
    public FoodItemService(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    public String getFoodCategoryOrderedMost()
    {
        // Initialize variables to keep track of the most ordered category and its count
        String mostOrderedCategory = null;
        int maxCount = 0;

        // Retrieve all orders
        List<OrderEntity> orders = orderEntityRepository.findAll();

        // Iterate through orders and count food categories
        for (OrderEntity order : orders)
        {
            for (FoodItem foodItem : order.getFoodItems())
            {
                String category = foodItem.getMenu().getCategory().toString();
                int categoryCount = 1; // Initialize count for the current category

                // Check if this category has been seen before
                if (category.equals(mostOrderedCategory))
                {
                    categoryCount = maxCount + 1; // Increment count
                }

                // Check if the current category has a higher count
                if (categoryCount > maxCount)
                {
                    maxCount = categoryCount; // Update max count
                    mostOrderedCategory = category; // Update most ordered category
                }
            }
        }

        return mostOrderedCategory;
    }



}
