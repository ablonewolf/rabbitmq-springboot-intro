package com.ablonewolf.rabbitmq_producer.util;

import com.github.javafaker.Faker;

public class RandomNameGenerator {

    private static final Faker faker = Faker.instance();

    public static String generateRandomName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateRandomId() {
        return faker.idNumber().toString();
    }
}


