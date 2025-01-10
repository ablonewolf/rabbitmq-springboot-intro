package com.ablonewolf.rabbitmq_producer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Furniture {
    private String color;
    private String material;
    private String name;
    private Integer price;
}
