package com.ablonewolf.rabbitmq_producer;

import com.ablonewolf.rabbitmq_producer.model.Employee;
import com.ablonewolf.rabbitmq_producer.model.Picture;
import com.ablonewolf.rabbitmq_producer.producer.EmployeePublisherComponent;
import com.ablonewolf.rabbitmq_producer.producer.PictureProducer;
import com.ablonewolf.rabbitmq_producer.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.List;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class RabbitmqProducerApplication implements CommandLineRunner {

    private final EmployeePublisherComponent employeePublisherComponent;
    private final PictureProducer pictureProducer;

    // valid sources
    private final List<String> SOURCES = List.of("MOBILE", "WEB");

    // valid types
    private final List<String> TYPES = List.of("jpg", "png", "svg");

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 10; i++) {
            var employee = new Employee(Helper.generateRandomId(),
                Helper.generateRandomName(),
                LocalDate.now());
            employeePublisherComponent.publishEmployeeFromHRExchange(employee);

            String pictureName = Helper.generatePictureName();
            Long pictureSize = Helper.generateRandomSize();
            String pictureType = TYPES.get(i % TYPES.size());
            String pictureSource = SOURCES.get(i % SOURCES.size());

            var picture = Picture.builder()
                .name(pictureName)
                .size(pictureSize)
                .type(pictureType)
                .source(pictureSource)
                .build();

            pictureProducer.sendPicture(picture);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

}
