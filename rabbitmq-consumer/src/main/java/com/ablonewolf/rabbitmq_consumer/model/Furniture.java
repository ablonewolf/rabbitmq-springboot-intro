package com.ablonewolf.rabbitmq_consumer.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Furniture {
    private String color;
    private String material;
    private String name;
    private Integer price;
}
