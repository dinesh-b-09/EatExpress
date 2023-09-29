package com.example.EatExpress.model;

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
@Table(name = "cart")
public class Cart
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;


        double cartTotal;


        @OneToOne
        @JoinColumn
        Customer customer;


        @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
        List<FoodItem> foodItems = new ArrayList<>();

    }
