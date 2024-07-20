package com.example.paymentservice.controllers;

import com.stripe.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class WebhookController {

    @PostMapping("/stripeWebhook/paymentSuccessful")
    public void onStripePaymentCompleteEvent(Event event) {
        System.out.println("payment complete");
    }
}
