package com.lee.spring.rabbitmq.service.impl;

import com.lee.spring.rabbitmq.service.MessageSendService;
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
 * 简单队列生产者发送消息
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
        // 对于简单队列来说,路由键就是队列名称,消息会发送到默认的交换机上,然后交换机将消息转发到该队列中去
        rabbitTemplate.convertAndSend(timerQueueName, msg);
    }

    /**
     * 启动生产者确认模式(confirm模式),需要2个步骤:
     * 1.设置publisherConfirms = true
     * 2.在发送消息时,加入CorrelationData,它里面有个ID,在发送成功以后的confirm回调可以通过这个id,判断唯一一条消息
     *
     * @param message
     */
    @Override
    public String sendMsgWithResult(Object message) {
        // 转换并发送消息,且等待消息者返回响应消息。
        Object o = rabbitTemplate.convertSendAndReceive(dateQueueName, message, new CorrelationData(System.currentTimeMillis() + ""));
        return (String) o;
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
        if (ack) {
            System.out.println("confirm#####消息发送成功: id=" + correlationData.getId());
        } else {
            System.out.println("confirm#####消息发送失败: id=" + correlationData.getId());
        }
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
