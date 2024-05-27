package com.example.paymentservice.dtos;

import com.example.paymentservice.models.PaymentGatewayNames;
import lombok.Data;

@Data
public class PaymentLinkReqDTO {
    Long orderId;
    PaymentGatewayNames paymentGateway;
}
