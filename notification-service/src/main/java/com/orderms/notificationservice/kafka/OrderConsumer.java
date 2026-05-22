package com.orderms.notificationservice.kafka;

import com.orderms.notificationservice.NotificationServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceApplication.class);


    @KafkaListener(topics = "order-created-topic", groupId = "notification-group")
    public void consume(String message) {

        logger.info("==================================");
        logger.info("Order Event Received");
        logger.info(message);
        logger.info("Notification sent to customer");
        logger.info("==================================");
    }
}