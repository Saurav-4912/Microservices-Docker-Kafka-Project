package com.orderms.notificationservice.kafka;

import com.orderms.notificationservice.NotificationServiceApplication;
import com.orderms.notificationservice.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceApplication.class);


    @KafkaListener(topics = "order-created-topic", groupId = "notification-group")
    public void consume(OrderCreatedEvent event) {

        logger.info("Order event received for orderId={}, product={}, customer={}",
                event.getOrderId(),
                event.getProductName(),
                event.getCustomerEmail());
    }
}