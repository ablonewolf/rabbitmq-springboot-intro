package com.ablonewolf.rabbitmq_consumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Picture {
    private String name;
    private String type;
    private String source;
    private Long size;
}
