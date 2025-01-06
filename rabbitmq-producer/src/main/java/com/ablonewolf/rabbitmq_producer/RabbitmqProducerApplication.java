package com.ablonewolf.rabbitmq_producer;

import com.ablonewolf.rabbitmq_producer.model.Employee;
import com.ablonewolf.rabbitmq_producer.producer.RabbitMQProducerComponent;
import com.ablonewolf.rabbitmq_producer.util.RandomNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class RabbitmqProducerApplication implements CommandLineRunner {

    private final RabbitMQProducerComponent rabbitMQProducerComponent;

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 10; i++) {
            var employee = new Employee(RandomNameGenerator.generateRandomId(),
                RandomNameGenerator.generateRandomName(),
                LocalDate.now());
            rabbitMQProducerComponent.publishEmployeeToQueue(employee);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

}
