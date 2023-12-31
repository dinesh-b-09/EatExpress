package com.example.EatExpress.dto.responseDTO;

import com.example.EatExpress.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerResponse
{

    String name;

    String email;

    String address;

    String mobileNo;

    Gender gender;

    double cartTotal;

    List<MenuResponse> foodItems;

}
