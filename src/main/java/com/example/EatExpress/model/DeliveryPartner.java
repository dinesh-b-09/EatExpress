package com.example.EatExpress.model;

import com.example.EatExpress.Enum.Gender;
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
@Table(name = "delivery_partner")
public class DeliveryPartner
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;


        String name;


        @Column(unique = true, nullable = false)
        @Size(min = 10, max = 10)
        String mobileNo;


        @Enumerated(EnumType.STRING)
        Gender gender;


        @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
        List<OrderEntity> orders = new ArrayList<>();

        // can add enum : status- active/not
}
