package com.lee.rabbitmq._1_simple;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/22 11:17
 * @description 简单模拟接收消息
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMQUtils.QUEUE_NAME, false, false, false, null);
        // 示例1
        // 定义接收到消息以后的回调
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(LocalDate.now() + "接收到了消息: " + message);
//        };
//        // 消息取消以后的回调
//        CancelCallback cancelCallback = consumerTag -> System.out.println("消息被取消了");
//        channel.basicConsume(RabbitMQUtils.QUEUE_NAME, true, deliverCallback, cancelCallback);
        // 示例2
        channel.basicConsume(RabbitMQUtils.QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey(); // 队列名称
                String contentType = properties.getContentType(); // 内容类型
                String message = new String(body, "UTF-8"); // 消息正文
                System.out.printf("routingKey: [%s], contentType: [%s], message: [%s] \n", routingKey, contentType, message);
            }
        });
    }
}
