package com.lee.spring.rabbitmq.service;

import com.lee.spring.rabbitmq.model.OrderMsg;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Topic模式下消息消费者
 *
 * @author lee
 * @date 2020/3/26 11:06
 */
public interface OrderMessageReceiveService {
    void processAdd(OrderMsg msg, Channel channel, long tag);

    void processDelete(OrderMsg msg, Channel channel, long tag);

    void processAll(OrderMsg msg, Channel channel, long tag);
}
