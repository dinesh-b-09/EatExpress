package com.example.EatExpress.model;

import com.example.EatExpress.Enum.RestaurantCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Table(name = "restaurant")
public class Restaurant
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;


        String name;


        String location;


        @Enumerated(EnumType.STRING)
        RestaurantCategory restaurantCategory;


        @Column(unique = true,nullable = false)
        @Size(min = 10, max = 10)
        String contactNumber;


        boolean opened;


        @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
        List<Menu> availableMenuItems = new ArrayList<>();


        @OneToMany(mappedBy = "restaurant" ,cascade = CascadeType.ALL)
        List<OrderEntity> orders = new ArrayList<>();
}
