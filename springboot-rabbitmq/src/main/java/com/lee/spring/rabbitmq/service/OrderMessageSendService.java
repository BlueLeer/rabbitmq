package com.lee.spring.rabbitmq.service;

import com.lee.spring.rabbitmq.model.OrderMsg;

/**
 * Topic模式下的消息发送服务
 *
 * @author lee
 * @date 2020/3/26 10:48
 */
public interface OrderMessageSendService {
    
    void sendOrderDeleteMsg(OrderMsg message);

    void sendOrderAddMsg(OrderMsg message);
}
