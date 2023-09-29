package com.example.EatExpress.model;

import com.example.EatExpress.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "food_item")
public class FoodItem
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;

        int requiredQuantity;

        double totalCost;   // will be calculated backend


        @ManyToOne
        @JoinColumn
        Cart cart;


        @ManyToOne
        @JoinColumn
        Menu menu;



        @ManyToOne
        @JoinColumn
        OrderEntity order;

}
