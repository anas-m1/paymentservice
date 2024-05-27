package com.example.paymentservice.models;

public interface IPaymentGateway {

    String getPaymentLink(Long orderId);
}
