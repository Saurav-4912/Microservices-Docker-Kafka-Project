package com.orderms.orderservice.service;

import com.orderms.orderservice.dto.CreateOrderRequest;
import com.orderms.orderservice.dto.OrderResponse;
import com.orderms.orderservice.entity.Order;
import com.orderms.orderservice.entity.OrderStatus;
import com.orderms.orderservice.event.OrderCreatedEvent;
import com.orderms.orderservice.kafka.OrderProducer;
import com.orderms.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .customerEmail(request.getCustomerEmail())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(savedOrder.getId())
                .productName(savedOrder.getProductName())
                .quantity(savedOrder.getQuantity())
                .price(savedOrder.getPrice())
                .customerEmail(savedOrder.getCustomerEmail())
                .status(savedOrder.getStatus().name())
                .createdAt(savedOrder.getCreatedAt())
                .build();

        orderProducer.sendOrderCreatedEvent(event);

        return mapToResponse(savedOrder);
    }

    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .customerEmail(order.getCustomerEmail())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}