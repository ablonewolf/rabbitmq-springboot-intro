package com.ablonewolf.rabbitmq_producer;

import com.ablonewolf.rabbitmq_producer.service.RabbitMQProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RequiredArgsConstructor
public class RabbitmqProducerApplication implements CommandLineRunner {

    private final RabbitMQProducerService rabbitMQProducerService;

    @Override
    public void run(String... args) {
        rabbitMQProducerService.sendMessage("Thread Name: " + ThreadLocalRandom.current().nextInt());
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

}
