package com.orderms.orderservice.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {

    private Long orderId;
    private String productName;
    private Integer quantity;
    private Double price;
    private String customerEmail;
    private String status;
    private LocalDateTime createdAt;
}
