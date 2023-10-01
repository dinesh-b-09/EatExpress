package com.example.EatExpress.service;

import com.example.EatExpress.dto.requestDTO.DeliveryPartnerRequest;
import com.example.EatExpress.dto.responseDTO.DeliveryPartnerResponse;
import com.example.EatExpress.model.Customer;
import com.example.EatExpress.model.DeliveryPartner;
import com.example.EatExpress.repository.DeliveryPartnerRepository;
import com.example.EatExpress.transformer.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public String partnerWithMaxDeliveries()
    {
//        List<DeliveryPartner> allDeliveryPartners = deliveryPartnerRepository.findAll();
//
//        String partner = "";
//        int maxDeliveries = -1;
//
//        for (DeliveryPartner dp : allDeliveryPartners)
//        {
//            int numberOfDeliveries = dp.getOrders().size();
//            if (numberOfDeliveries > maxDeliveries) {
//                maxDeliveries = numberOfDeliveries;
//                partner = dp.getName();
//            }
//        }
//        return partner;

        DeliveryPartner partner = deliveryPartnerRepository.findPartnerHighestDeliveries();

        if(partner != null)
        {
            return partner.getName();
        }

        return "Delivery Partner did not deliver any order to the customers";
    }
}
