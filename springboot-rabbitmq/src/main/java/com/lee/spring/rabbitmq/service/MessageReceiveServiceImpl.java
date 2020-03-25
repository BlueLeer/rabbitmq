package com.lee.spring.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2020/3/26 0:28
 */
@Component
public class MessageReceiveServiceImpl implements MessageReceiveService {
    @RabbitListener(queues = "queue_timer")
    @RabbitHandler
    @Override
    public void process(String msg) {
        System.out.println("##########" + msg);
    }

    @RabbitListener(queues = "queue_date")
    @RabbitHandler
    @Override
    public Boolean processWithResp(String msg) {
        System.out.println("##########" + msg);
        return true;
    }
}
