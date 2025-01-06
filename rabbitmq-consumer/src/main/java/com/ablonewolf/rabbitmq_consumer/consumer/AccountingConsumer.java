package com.ablonewolf.rabbitmq_consumer.consumer;

import com.ablonewolf.rabbitmq_consumer.configuration.RabbitMQConfig;
import com.ablonewolf.rabbitmq_consumer.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountingConsumer {

    private static final Logger log = LoggerFactory.getLogger(AccountingConsumer.class);
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.ACCOUNTING_QUEUE)
    public void consumeEmployee(String message) {
        try {
            var employee = objectMapper.readValue(message, Employee.class);
            log.info("Received employee object from Accounting Queue: {}", employee);
        } catch (JsonProcessingException e) {
            log.error("An exception occurred while deserializing employee object: {}", e.getMessage());
        }
    }
}
