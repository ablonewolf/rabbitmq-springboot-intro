package com.ablonewolf.rabbitmq_producer.util;

import com.github.javafaker.Faker;

public class Helper {

    private static final Faker faker = Faker.instance();

    public static String generateRandomName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateRandomId() {
        return String.valueOf(faker.random().nextInt(100));
    }

    public static String generatePictureName() {
        return faker.pokemon().name();
    }

    public static Long generateRandomSize() {
        return faker.random().nextLong(10000);
    }
}


