package com.lee.spring.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySources;

/**
 * @author lee
 * @date 2020/3/25 23:24
 */
@Configuration
public class RabbitConfig {
    @Value("${lee.rabbitmq.queue-timer}")
    private String timerQueueName;

    @Value("${lee.rabbitmq.queue-date}")
    private String dateQueueName;

    @Bean
    public Queue timerQueue() {
        return new Queue(timerQueueName);
    }

    @Bean
    public Queue dateQueue() {
        return new Queue(dateQueueName);
    }
}
