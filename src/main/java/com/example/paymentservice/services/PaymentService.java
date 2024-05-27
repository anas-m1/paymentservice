package com.example.paymentservice.services;

import com.example.paymentservice.models.IPaymentGateway;
import com.example.paymentservice.models.PaymentGatewayNames;
import com.example.paymentservice.models.RazorpayPG;
import com.example.paymentservice.models.StripePG;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String generatePaymentLink(Long orderId, PaymentGatewayNames pgName) {
        IPaymentGateway pg = null;
        if(pgName == PaymentGatewayNames.STRIPE) pg = new StripePG();
        else if(pgName == PaymentGatewayNames.RAZORPAY) pg = new RazorpayPG();
        System.out.println(orderId +"in payment service");
        return pg.getPaymentLink(orderId);
    }
}

