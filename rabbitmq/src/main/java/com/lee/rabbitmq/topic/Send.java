package com.lee.rabbitmq.topic;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/28 16:04
 * @description router(路由) 模式
 * 特点:
 * 1.发送消息时,带上routingKey
 * 2.消费者绑定交换机的时候传入自己想监听的routingKey
 * 3.服务端发送消息时会自动路由到匹配的routingKey
 */
public class Send {
    public static final String EXCHANGE_NAME = "topic_exchange_3";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        try (
                Connection connection = RabbitMQUtils.getConnection();
                Channel channel = connection.createChannel();
        ) {
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            for (int i = 0; i < 10; i++) {
                String routingKey = i % 2 == 0 ? "order.a.delete.lee" : "item.add.lee";
                String message = routingKey.equals("order.a.delete.lee") ? "商品删除了:" + i : "商品添加了:" + i;
                channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
                System.out.printf("[send msg] %S \n", message);
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
