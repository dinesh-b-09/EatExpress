package com.example.EatExpress.dto.responseDTO;

import com.example.EatExpress.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartnerResponse
{
    String name;

    String mobileNo;

    Gender gender;

    String email;
}
