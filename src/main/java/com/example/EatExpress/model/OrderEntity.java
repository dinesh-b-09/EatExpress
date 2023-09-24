package com.example.EatExpress.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "order_entity")
public class OrderEntity
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;


        String orderId;   // UUID


        Date orderTime;


        @ManyToOne
        @JoinColumn
        Customer customer;


        @ManyToOne
        @JoinColumn
        DeliveryPartner deliveryPartner;


        @ManyToOne
        @JoinColumn
        Restaurant restaurant;


        @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
        List<FoodItem> foodItems = new ArrayList<>();
}
