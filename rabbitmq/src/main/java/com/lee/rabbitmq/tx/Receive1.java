package com.lee.rabbitmq.tx;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/29 12:10
 * @description
 */
public class Receive1 {
    public static final String QUEUE_NAME = "tx_queue_name_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("[receive 1] " + msg);

            }
        };
        channel.basicConsume(QUEUE_NAME, consumer);
    }
}
