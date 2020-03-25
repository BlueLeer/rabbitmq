package com.lee.spring.rabbitmq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 发送当前时间到队列中去
 *
 * @author lee
 * @date 2020/3/25 23:28
 */
@Component
public class MessageSendServiceImpl implements MessageSendService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${lee.rabbitmq.queue-timer}")
    private String timerQueueName;

    @Value("${lee.rabbitmq.queue-date}")
    private String dateQueueName;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void sendMsg(String message) {
        String msg = message + LocalDateTime.now().format(DateTimeFormatter.ofPattern("-----yyyy年MM月dd日 HH时mm分ss秒"));
//        rabbitTemplate.send(timerQueueName, new Message("我是王大红".getBytes(), null));
        // 转换并发送消息,它会将参数对象转换成Message对象,然后再发送
        rabbitTemplate.convertAndSend(timerQueueName, msg);
    }

    @Override
    public void sendMsgWithResp(String message) {
        // 转换并发送消息,且等待消息者返回响应消息。
        Object hello = rabbitTemplate.convertSendAndReceive(dateQueueName, message);
        System.out.println(hello);
    }

    /**
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msg = ack ? "消息发送成功" : "消息发送失败";
        String id = correlationData.getId();
        System.out.println("id: " + id + " " + msg);
    }

    /**
     * ReturnCallback接口用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调。
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getCorrelationId() + " 发送失败");
    }
}
