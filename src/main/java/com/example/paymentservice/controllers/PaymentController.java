package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.PaymentLinkReqDTO;
import com.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.*;

//why restcontroler instead of controller ---> restcontroller gives response in json or xml in addition to controller functionality
@RestController
public class PaymentController {
    PaymentService paymentService;

    PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/payment")
    String genereatePaymentLink(@RequestBody PaymentLinkReqDTO paymentLinkReqDTO){
        System.out.println(paymentLinkReqDTO.getOrderId());
        return paymentService.generatePaymentLink(paymentLinkReqDTO.getOrderId(),paymentLinkReqDTO.getPaymentGateway());
    }
}
