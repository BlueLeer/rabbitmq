package com.lee.rabbitmq.work2;

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

    public static final String QUEUE_NAME = "work_queue_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 该队列已经在发送者的代码中声明了,所以在这里可以不用声明该队列
//        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 一次只消费一个消息,也就是说生产者一次只发送一条消息
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

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
                } finally {
                    System.out.printf("[receive 1] 收到的 delivery tag为: %n", envelope.getDeliveryTag());
                    // 手动确认消息【参数说明，参数1：该消息的index；参数2：是否批量应答，true：批量确认消息index的消息】
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        // 消息的确认模式:手动确认
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

}
