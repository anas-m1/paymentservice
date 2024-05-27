package com.example.paymentservice.models;

import com.example.paymentservice.dtos.OrderResponseDTO;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RazorpayPG implements IPaymentGateway {

    @Value("${razorpay.api.key}")
    String apiKey;

    @Value("${razorpay.api.secret}")
    String apiSecret;

    @Override
    public String getPaymentLink(Long orderId) {
        RestTemplate rt=new RestTemplate();
        System.out.println(orderId + "order id from razorpay pg");
//        OrderResponseDTO orderDetails= rt.getForEntity("http://localhost:8088/orders/{orderId}}", OrderResponseDTO.class).getBody();
//        Long amount=orderDetails.getAmount();
        Long amount =100L;



        try {
            RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);

            paymentLinkRequest.put("callback_url", "https://google.com");
            paymentLinkRequest.put("callback_method", "get");

//            paymentLinkRequest.put("reference_id", "#aasasw8");
            paymentLinkRequest.put("first_min_partial_amount", 100);
            paymentLinkRequest.put("description", "Payment for policy no #23456");
            JSONObject customer = new JSONObject();
            customer.put("name", "+919000090000");
            customer.put("contact", "Gaurav Kumar");
            customer.put("email", "gaurav.kumar@example.com");
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);

            JSONObject options = new JSONObject();
            JSONObject notes = new JSONObject();
            notes.put("branch", "Acme Corp Bangalore North");
            notes.put("name", "Bhairav Kumar");

            JSONObject theme = new JSONObject();
            theme.put("hide_topbar", true);
            JSONObject checkout = new JSONObject();
            checkout.put("theme", theme);
            options.put("checkout", checkout);
            paymentLinkRequest.put("options", options);

            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
            System.out.println((String) payment.get("short_url"));
            return payment.get("short_url");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
