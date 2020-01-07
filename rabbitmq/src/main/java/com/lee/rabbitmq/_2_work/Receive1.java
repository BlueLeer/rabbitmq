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
public class Receive1 {

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
                System.out.printf("[receive 1] %s \n", msg);
                // 休眠1秒,模拟收到消息以后进行的逻辑处理
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        // 消息的确认模式:手动应答
        /* 如果消息的确认模式为手动应答时,当接收到消息以后,如果没有应答(即调用channel.basicAck(envelope.getDeliveryTag(),false);)
        Rabbit将不会发送更多的消息给该消费者了,这是因为Rabbit认为你没有做好接收消息的准备(待验证,当前项目中验证失败)
        消息就会在队列中从Ready变为Unacked状态,
        */
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

}
