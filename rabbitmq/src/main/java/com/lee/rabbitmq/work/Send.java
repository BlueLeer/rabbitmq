package com.lee.rabbitmq.work;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/28 14:40
 * @description 这种处理方式是 "轮询分发" ,消费者接收到的消息数量都是一样的,你一个我一个,不会因为谁处理消息消耗时间短而处理消息会变多
 */
public class Send {
    public static final String QUEUE_NAME = "work_queue_1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        try (
                // 获取连接
                Connection connection = RabbitMQUtils.getConnection();
                // 获取通道
                Channel channel = connection.createChannel();
        ) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 50; i++) {
                String msg = "hello " + i;
                channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
                System.out.printf("[send msg] %s \n", msg);
                // 睡眠1秒
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

}
