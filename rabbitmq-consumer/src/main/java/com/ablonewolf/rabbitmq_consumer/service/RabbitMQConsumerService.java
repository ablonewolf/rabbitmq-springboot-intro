package com.ablonewolf.rabbitmq_consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumerService.class);

    @RabbitListener(queues = "helloQueue")
    public void listen(String message) {
        log.info("Consuming message: {}", message);
    }
}
