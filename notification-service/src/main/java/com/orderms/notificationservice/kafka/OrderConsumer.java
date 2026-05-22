package com.orderms.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "order-created-topic", groupId = "notification-group")
    public void consume(String message) {

        System.out.println("==================================");
        System.out.println("Order Event Received");
        System.out.println(message);
        System.out.println("Notification sent to customer");
        System.out.println("==================================");
    }
}