package com.lee.spring.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic模式,主要配置三个角色:Queue,Exchange,Binding
 *
 * @author lee
 * @date 2020/3/26 10:30
 */
@Configuration
public class TopicRabbitConfig {
    @Value("${lee.rabbitmq.queue-order-add}")
    private String orderAddQueueName;


    @Value("${lee.rabbitmq.queue-order-delete}")
    private String orderDeleteQueueName;


    @Value("${lee.rabbitmq.queue-order-all}")
    private String orderAllQueueName;

    @Value("${lee.rabbitmq.exchange-order-all}")
    private String orderAllExchangeName;

    @Bean
    public Queue orderAddQueue() {
        return new Queue(orderAddQueueName);
    }

    @Bean
    public Queue orderDeleteQueue() {
        return new Queue(orderDeleteQueueName);
    }

    @Bean
    public Queue orderAllQueue() {
        return new Queue(orderAllQueueName);
    }

    @Bean
    public TopicExchange allTopicExchange() {
        // 参数说明:交换机名称、是否支持持久化、当所有消费者客户端断开连接以后，是否自动删除队列
        TopicExchange topicExchange = new TopicExchange(orderAllExchangeName, true, false);
        return topicExchange;
    }

    @Bean
    public Binding deleteBinding(Queue orderDeleteQueue, TopicExchange allTopicExchange) {
        return BindingBuilder.bind(orderDeleteQueue).to(allTopicExchange).with("order.delete");
    }

    @Bean
    public Binding addBinding(Queue orderAddQueue, TopicExchange allTopicExchange) {
        return BindingBuilder.bind(orderAddQueue).to(allTopicExchange).with("order.add");
    }

    @Bean
    public Binding allBinding(Queue orderAllQueue, TopicExchange allTopicExchange) {
        // 再次强调:星号(*)代表一个词,#代表0个或者多个词
        return BindingBuilder.bind(orderAllQueue).to(allTopicExchange).with("order.*");
    }
}
