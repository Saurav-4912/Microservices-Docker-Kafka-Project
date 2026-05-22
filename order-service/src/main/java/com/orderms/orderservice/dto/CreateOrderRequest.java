package com.orderms.orderservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private String productName;
    private Integer quantity;
    private Double price;
    private String customerEmail;
}