package com.lee.spring.rabbitmq.service.impl;

import com.lee.spring.rabbitmq.model.OrderMsg;
import com.lee.spring.rabbitmq.service.OrderMessageReceiveService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author lee
 * @date 2020/3/26 11:07
 */
@Component
public class OrderMessageReceiveServiceImpl implements OrderMessageReceiveService {
    @RabbitListener(queues = "queue_order_add")
    @RabbitHandler
    @Override
    public void processAdd(OrderMsg msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        String tName = Thread.currentThread().getId()+"";
        if (!msg.getType().equals("add")) {
            System.out.println("订单错误");
        } else {
            System.out.println(tName + ":订单###" + msg.getId() + "###正在处理中!");
            try {
                channel.basicAck(tag, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = "queue_order_delete")
    @RabbitHandler
    @Override
    public void processDelete(OrderMsg msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        String tName = Thread.currentThread().getId()+"";
        if (!msg.getType().equals("delete")) {
            System.out.println("订单错误");
        } else {
            System.out.println(tName + ":订单###" + msg.getId() + "###正在处理中!");
            try {
                channel.basicAck(tag, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = "queue_order_all")
    @RabbitHandler
    @Override
    public void processAll(OrderMsg msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        String tName = Thread.currentThread().getId()+"";
        System.out.println(tName + ":订单###" + msg.getId() + "###已经记录日志了!");
        try {
            channel.basicAck(tag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
