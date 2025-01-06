package com.ablonewolf.rabbitmq_producer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String HR_EXCHANGE = "hr.exchange";
    public static final String ACCOUNTING_QUEUE = "accounting.queue";
    public static final String MARKETING_QUEUE = "marketing.queue";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue accountingQueue() {
        return new Queue(ACCOUNTING_QUEUE, true);
    }

    @Bean
    public Queue marketingQueue() {
        return new Queue(MARKETING_QUEUE, true);
    }

    @Bean
    public FanoutExchange getHRExchange() {
        return new FanoutExchange(HR_EXCHANGE);
    }

    @Bean
    public Binding accountingBinding() {
        return BindingBuilder.bind(accountingQueue()).to(getHRExchange());
    }

    @Bean
    public Binding marketingBinding() {
        return BindingBuilder.bind(marketingQueue()).to(getHRExchange());
    }

}
