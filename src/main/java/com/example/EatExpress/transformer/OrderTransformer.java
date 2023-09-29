package com.example.EatExpress.transformer;

import com.example.EatExpress.dto.responseDTO.FoodItemResponse;
import com.example.EatExpress.dto.responseDTO.OrderResponse;
import com.example.EatExpress.model.Cart;
import com.example.EatExpress.model.FoodItem;
import com.example.EatExpress.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer
{
    public static OrderEntity prepareOrderEntity(Cart cart){
        return OrderEntity.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .orderTotal(cart.getCartTotal())
                .build();
    }

    public static OrderResponse OrderToOrderResponse(OrderEntity savedOrder) {

        List<FoodItemResponse> foodResponseList = new ArrayList<>();
        for(FoodItem food: savedOrder.getFoodItems()){
            FoodItemResponse foodResponse = FoodItemResponse.builder()
                    .dishName(food.getMenu().getDishName())
                    .price(food.getMenu().getPrice())
                    .category(food.getMenu().getCategory())
                    .veg(food.getMenu().isVeg())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();

            foodResponseList.add(foodResponse);
        }

        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .orderTime(savedOrder.getOrderTime())
                .orderTotal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobile(savedOrder.getCustomer().getMobileNo())
                .deliveryPartnerName(savedOrder.getDeliveryPartner().getName())
                .deliveryPartnerMobile(savedOrder.getDeliveryPartner().getMobileNo())
                .restaurantName(savedOrder.getRestaurant().getName())
                .foodResponses(foodResponseList)
                .build();
    }
}
