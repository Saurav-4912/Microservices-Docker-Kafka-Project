package com.orderms.notificationservice.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderCreatedEvent {

    private Long orderId;
    private String productName;
    private Integer quantity;
    private Double price;
    private String customerEmail;
    private String status;
    private LocalDateTime createdAt;
}
