package com.ablonewolf.rabbitmq_producer.producer;

import com.ablonewolf.rabbitmq_producer.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducerComponent {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(RabbitMQProducerComponent.class);

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("helloQueue", message);
    }

    public void publishEmployeeToQueue(Employee employee) {
        try {
            var jsonObject = objectMapper.writeValueAsString(employee);
            rabbitTemplate.convertAndSend("employeeQueue", jsonObject);
        } catch (JsonProcessingException e) {
            log.error("An error occurred while serializing employee to json, details: {}", e.getMessage());
        }
    }



/*
    @Scheduled(fixedRate = 500)
    public void sendMessageAtFixedRate() {
        log.info("i is {}", i);
        rabbitTemplate.convertAndSend("fixedRate", "Current value of i is: " + i);
        ++i;
    }
*/
}
