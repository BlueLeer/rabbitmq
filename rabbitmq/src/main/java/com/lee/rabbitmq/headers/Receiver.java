package com.lee.rabbitmq.headers;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author lee
 * @date 2020/3/25 17:23
 */
public class Receiver {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("headers_exchange", BuiltinExchangeType.HEADERS);
        Map<String, Object> queueParams = new HashMap<>();
        queueParams.put("name", "wangle");
        queueParams.put("age", "18");
        channel.queueDeclare("headers_queue", false, false, false, queueParams);

        Map<String, Object> bindParams = new HashMap<>();
        bindParams.put("name", "wangle");
        queueParams.put("age", "18");
        channel.queueBind("headers_queue", "headers_exchange", "", bindParams);

        channel.basicConsume("headers_queue", new DefaultConsumer(channel) {
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
