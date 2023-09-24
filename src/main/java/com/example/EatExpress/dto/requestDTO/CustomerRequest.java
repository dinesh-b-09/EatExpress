package com.example.EatExpress.dto.requestDTO;

import com.example.EatExpress.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerRequest
{

    String name;


    String email;


    String address;


    String mobileNo;


    Gender gender;

}
