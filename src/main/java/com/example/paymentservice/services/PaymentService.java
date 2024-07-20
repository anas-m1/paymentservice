package com.example.paymentservice.services;

import com.example.paymentservice.models.IPaymentGateway;
import com.example.paymentservice.models.PaymentGatewayNames;
import com.example.paymentservice.models.RazorpayPG;
import com.example.paymentservice.models.StripePG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    StripePG stripePG;

    @Autowired
    StripePG razorpayPG;

    public String generatePaymentLink(Long orderId, PaymentGatewayNames pgName) {
        IPaymentGateway pg = null;
        if(pgName == PaymentGatewayNames.STRIPE) pg = stripePG;
        else if(pgName == PaymentGatewayNames.RAZORPAY) pg = razorpayPG;
        System.out.println(orderId +"in payment service");
        return pg.getPaymentLink(orderId);
    }
}

