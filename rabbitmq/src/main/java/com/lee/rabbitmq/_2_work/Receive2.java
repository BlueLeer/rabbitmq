package com.lee.rabbitmq._2_work;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/28 14:45
 * @description
 */
public class Receive2 {

    public static final String QUEUE_NAME = "work_queue_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义一个消息的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.printf("[receive 2] %s \n", msg);
                // 休眠1秒,模拟收到消息以后进行的逻辑处理
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 消息的确认模式:手动应答
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

}
