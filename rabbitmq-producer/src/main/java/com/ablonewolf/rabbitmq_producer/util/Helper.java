package com.ablonewolf.rabbitmq_producer.util;

import com.github.javafaker.Faker;

import java.util.List;

public class Helper {

    private static final Faker faker = Faker.instance();
    private static final List<String> MATERIALS = List.of("wood", "plastic", "steel");
    private static final List<String> COLORS = List.of("red", "green", "white");

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

    public static String generateRandomColor() {
        int index = faker.random().nextInt(COLORS.size());
        return COLORS.get(index);
    }

    public static String generateRandomMaterial() {
        int randomMaterial = faker.random().nextInt(MATERIALS.size());
        return MATERIALS.get(randomMaterial);
    }

    public static String generateRandomFurnitureName() {
        return faker.funnyName().name();
    }

    public static Integer generateRandomPrice() {
        return faker.random().nextInt(5000, 10000);
    }
}


