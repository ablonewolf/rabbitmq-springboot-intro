package com.ablonewolf.rabbitmq_producer.producer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;
    private static final Logger log = LoggerFactory.getLogger(RabbitMQProducerService.class);
    private Integer i = 1;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("helloQueue", message);
    }

    @Scheduled(fixedRate = 500)
    public void sendMessageAtFixedRate() {
        log.info("i is {}", i);
        rabbitTemplate.convertAndSend("fixedRate", "Current value of i is: " + i);
        ++i;
    }
}
