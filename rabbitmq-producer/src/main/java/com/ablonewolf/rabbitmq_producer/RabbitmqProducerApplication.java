package com.ablonewolf.rabbitmq_producer;

import com.ablonewolf.rabbitmq_producer.producer.RabbitMQProducerComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class RabbitmqProducerApplication implements CommandLineRunner {

    private final RabbitMQProducerComponent rabbitMQProducerComponent;

    @Override
    public void run(String... args) {
        rabbitMQProducerComponent.sendMessage("Thread Name: " + ThreadLocalRandom.current().nextInt());
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

}
