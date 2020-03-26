package com.lee.spring.rabbitmq.service.impl;

import com.lee.spring.rabbitmq.model.OrderMsg;
import com.lee.spring.rabbitmq.service.OrderMessageSendService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 注意:
 *
 * @author lee
 * @date 2020/3/26 10:57
 */
@Component
public class OrderMessageSendServiceImpl implements OrderMessageSendService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Value("${lee.rabbitmq.exchange-order-all}")
    private String orderAllExchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrderDeleteMsg(OrderMsg message) {
        Optional.ofNullable(message).ifPresent(o -> {
            ParameterizedTypeReference<Object> responseType = new ParameterizedTypeReference<Object>() {
            };

            rabbitTemplate.convertAndSend(orderAllExchangeName, "order.delete", o,
                    new CorrelationData(String.valueOf(message.getId())));
        });


    }

    @Override
    public void sendOrderAddMsg(OrderMsg message) {

        Optional.ofNullable(message).ifPresent(o -> {
            Object result = rabbitTemplate.convertSendAndReceive(orderAllExchangeName, "order.add", o);
            if (result instanceof OrderMsg) {
                System.out.println("订单未处理成功!");
            } else {
                System.out.println("订单处理成功!");
            }
        });
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功#####confirm: id=" + correlationData.getId());
        } else {
            System.out.println("消息发送失败#####confirm: id=" + correlationData.getId());

        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息发送失败了#####returnedMessage: replyCode=" + replyCode);
    }
}
