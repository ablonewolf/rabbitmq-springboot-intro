package com.ablonewolf.rabbitmq_producer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class Picture {
    private String name;
    private String type;
    private String source;
    private Long size;
}
