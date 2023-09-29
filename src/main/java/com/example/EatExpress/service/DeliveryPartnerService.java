package com.example.EatExpress.service;

import com.example.EatExpress.dto.requestDTO.DeliveryPartnerRequest;
import com.example.EatExpress.dto.responseDTO.DeliveryPartnerResponse;
import com.example.EatExpress.model.DeliveryPartner;
import com.example.EatExpress.repository.DeliveryPartnerRepository;
import com.example.EatExpress.transformer.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService
{
    final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    public DeliveryPartnerResponse addPartner(DeliveryPartnerRequest deliveryPartnerRequest)
    {

        //dto -> entity
        DeliveryPartner deliveryPartner = DeliveryPartnerTransformer.PartnerRequestToDeliveryPartner(deliveryPartnerRequest);

        // save
        DeliveryPartner savedPartner = deliveryPartnerRepository.save(deliveryPartner);

        // model --> response
        return DeliveryPartnerTransformer.DeliveryPartnertoDeliveryPartnerResponse(savedPartner);
    }
}
