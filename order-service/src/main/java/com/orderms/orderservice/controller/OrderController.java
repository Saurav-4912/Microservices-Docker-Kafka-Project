package com.orderms.orderservice.controller;

import com.orderms.orderservice.dto.CreateOrderRequest;
import com.orderms.orderservice.dto.OrderResponse;
import com.orderms.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/test")
    public String test() {
        return "Order Service is working";
    }
}