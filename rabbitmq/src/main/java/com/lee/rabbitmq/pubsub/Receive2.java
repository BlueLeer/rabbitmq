package com.lee.rabbitmq.pubsub;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/28 16:12
 * @description
 */
public class Receive2 {
    public static final String EXCHANGE_NAME = "ps_exchange_1";
    public static final String QUEUE_NAME_1 = "exchange_queue_name_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_1, false, false, false, null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, "");
        // 同一时刻,发送者只会发送一条消息到消费者
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.printf("[receive 2] %s \n", msg);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        // 开启手动应答模式(可以理解为关闭了自动应答模式,默认也是false)
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME_1, autoAck, consumer);

    }
}
