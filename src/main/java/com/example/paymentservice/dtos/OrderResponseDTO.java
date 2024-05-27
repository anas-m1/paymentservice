package com.example.paymentservice.dtos;

import lombok.Data;

@Data
public class OrderResponseDTO {
    Long amount;
    String description;
}
