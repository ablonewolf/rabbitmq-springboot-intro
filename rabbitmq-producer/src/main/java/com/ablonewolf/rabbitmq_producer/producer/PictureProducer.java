package com.ablonewolf.rabbitmq_producer.producer;

import com.ablonewolf.rabbitmq_producer.configuration.RabbitMQConfig;
import com.ablonewolf.rabbitmq_producer.model.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PictureProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(PictureProducer.class);

    public void sendPicture(Picture picture) {
        try {
            var pictureJSON = objectMapper.writeValueAsString(picture);
            rabbitTemplate.convertAndSend(RabbitMQConfig.PICTURE_EXCHANGE, picture.getType(), pictureJSON);
        } catch (JsonProcessingException e) {
            log.error("An error occurred while parsing the picture to JSON string, details: {}", e.getMessage());
        }
    }
}
