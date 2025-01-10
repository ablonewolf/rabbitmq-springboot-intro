package com.ablonewolf.rabbitmq_consumer.consumer;

import com.ablonewolf.rabbitmq_consumer.configuration.RabbitMQConfig;
import com.ablonewolf.rabbitmq_consumer.model.Furniture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscountQueueConsumer {
    private static final Logger log = LoggerFactory.getLogger(DiscountQueueConsumer.class);
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.DISCOUNT_QUEUE)
    public void listen(Message message) {
        try {
            var json = new String(message.getBody());
            var furniture = objectMapper.readValue(json, Furniture.class);
            log.info("Furniture received from the discount queue: {}", furniture);
        } catch (JsonProcessingException e) {
            log.error("An error occurred when parsing picture, details: {}", e.getMessage());
        }
    }
}