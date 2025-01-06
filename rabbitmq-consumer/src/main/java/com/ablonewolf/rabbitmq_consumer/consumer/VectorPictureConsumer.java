package com.ablonewolf.rabbitmq_consumer.consumer;

import com.ablonewolf.rabbitmq_consumer.configuration.RabbitMQConfig;
import com.ablonewolf.rabbitmq_consumer.model.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VectorPictureConsumer {
    private static final Logger log = LoggerFactory.getLogger(VectorPictureConsumer.class);
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.PICTURE_VECTOR_QUEUE)
    public void listen(String message) {
        try {
            var picture = objectMapper.readValue(message, Picture.class);
            log.info("Vector picture received: {}", picture);
        } catch (JsonProcessingException e) {
            log.error("An error occurred when parsing picture, details: {}", e.getMessage());
        }
    }
}
