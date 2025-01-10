package com.ablonewolf.rabbitmq_producer.producer;

import com.ablonewolf.rabbitmq_producer.configuration.RabbitMQConfig;
import com.ablonewolf.rabbitmq_producer.model.Furniture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FurnitureProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(FurnitureProducer.class);

    public void sendMessage(Furniture furniture) {
        var messageProperties = new MessageProperties();

        messageProperties.setHeader("color", furniture.getColor());
        messageProperties.setHeader("material", furniture.getMaterial());

        try {
            var jsonAsBytes = objectMapper.writeValueAsBytes(furniture);
            var message = new Message(jsonAsBytes, messageProperties);
            rabbitTemplate.send(RabbitMQConfig.PROMOTION_EXCHANGE, "", message);
        } catch (JsonProcessingException e) {
            log.error("An error occurred while serializing furniture, details: {}", e.getMessage());
        }
    }
}
