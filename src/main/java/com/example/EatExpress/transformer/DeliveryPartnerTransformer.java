package com.example.EatExpress.transformer;

import com.example.EatExpress.dto.requestDTO.DeliveryPartnerRequest;
import com.example.EatExpress.dto.responseDTO.DeliveryPartnerResponse;
import com.example.EatExpress.model.DeliveryPartner;

import java.util.ArrayList;

public class DeliveryPartnerTransformer
{
    public static DeliveryPartner PartnerRequestToDeliveryPartner(
            DeliveryPartnerRequest deliveryPartnerRequest)
    {

        return DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .gender(deliveryPartnerRequest.getGender())
                .email(deliveryPartnerRequest.getEmail())
                .orders(new ArrayList<>())
                .build();
    }

    public static DeliveryPartnerResponse DeliveryPartnertoDeliveryPartnerResponse(
            DeliveryPartner deliveryPartner)
    {
        return DeliveryPartnerResponse.builder()
                .name(deliveryPartner.getName())
                .mobileNo(deliveryPartner.getMobileNo())
                .gender(deliveryPartner.getGender())
                .email(deliveryPartner.getEmail())
                .build();
    }

}
