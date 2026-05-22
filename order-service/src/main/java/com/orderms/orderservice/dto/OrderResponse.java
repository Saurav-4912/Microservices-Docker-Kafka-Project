package com.orderms.orderservice.dto;

import com.orderms.orderservice.entity.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;
    private String productName;
    private Integer quantity;
    private Double price;
    private String customerEmail;
    private OrderStatus status;
    private LocalDateTime createdAt;
}