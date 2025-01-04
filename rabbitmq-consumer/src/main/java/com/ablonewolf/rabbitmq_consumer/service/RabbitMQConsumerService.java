package com.ablonewolf.rabbitmq_consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RabbitMQConsumerService {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumerService.class);

    @RabbitListener(queues = "helloQueue")
    public void listen(String message) {
        log.info("Consuming message: {}", message);
    }

    @RabbitListener(queues = "fixedRate", concurrency = "3")
    public void listenFixedRate(String message) {
        log.info("Thread {} consuming message: {}", Thread.currentThread().getName(), message);
        try {
            Thread.sleep(Duration.ofSeconds(2L));
        } catch (InterruptedException e) {
            log.error("An exception occurred while attempting to sleep, details: {}", e.getMessage());
        }
    }
}
