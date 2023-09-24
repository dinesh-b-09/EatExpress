package com.example.EatExpress.model;

import com.example.EatExpress.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "restaurant_menu")
public class Menu
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;


        String dishName;


        double price;


        @Enumerated(EnumType.STRING)
        FoodCategory category;


        boolean veg;


        boolean available;


        @ManyToOne
        @JoinColumn
        Restaurant restaurant;


        @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
        List<FoodItem> foodItems = new ArrayList<>();
}
