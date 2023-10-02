package com.example.EatExpress.service;

import com.example.EatExpress.dto.requestDTO.DeliveryPartnerRequest;
import com.example.EatExpress.dto.responseDTO.DeliveryPartnerResponse;
import com.example.EatExpress.model.DeliveryPartner;
import com.example.EatExpress.repository.DeliveryPartnerRepository;
import com.example.EatExpress.transformer.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryPartnerService
{
    final DeliveryPartnerRepository deliveryPartnerRepository;

    final JavaMailSender javaMailSender;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepository deliveryPartnerRepository, JavaMailSender javaMailSender) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.javaMailSender = javaMailSender;
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

        return "No Delivery Partner did not deliver any orders to the customers";
    }

    public List<DeliveryPartnerResponse> getsendEmailsToPartnersWithLessThanXDeliveries(int x)
    {
        List<DeliveryPartner> partnerList = deliveryPartnerRepository.findDeliveryPartnersWithLessThanXDeliveries(x);

        for (DeliveryPartner partner : partnerList)
        {
            String text = "Dear " + partner.getName() + ",\n\n"
                    + "You have done less than 10 deliveries. Please take action.\n\n"
                    + "Regards,\n"
                    + "Jon Snow.\n"
                    + "CEO of Game of Thrones.";

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("dumyacc1233@gmail.com");
            simpleMailMessage.setTo("vbcn12@gmail.com");
            simpleMailMessage.setSubject("Action Required: Low Delivery Count");
            simpleMailMessage.setText(text);

            javaMailSender.send(simpleMailMessage);
        }

        List<DeliveryPartnerResponse> list= new ArrayList<>();

        for(DeliveryPartner partner : partnerList)
        {
            list.add(DeliveryPartnerTransformer.DeliveryPartnertoDeliveryPartnerResponse(partner));
        }

        return list;
    }
}
