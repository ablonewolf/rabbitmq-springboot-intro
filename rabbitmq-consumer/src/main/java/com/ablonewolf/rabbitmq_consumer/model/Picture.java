package com.ablonewolf.rabbitmq_consumer.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Picture {
    private String name;
    private String type;
    private String source;
    private Long size;
}
