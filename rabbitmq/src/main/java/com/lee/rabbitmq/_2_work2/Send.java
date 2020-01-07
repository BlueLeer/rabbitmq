package com.lee.rabbitmq._2_work2;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/28 14:40
 * @description 这种处理方式是 "公平分发" ,消费者收到消息处理完以后,给发送者一个应答,告诉发送者,我已经处理完这条消息了,接着发送者
 * 就会发送下一条消息过来,这样显著的特点就是:处理消息快的消费者就会处理更多的消息
 */
public class Send {
    public static final String QUEUE_NAME = "work_queue_2";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        try (
                // 获取连接
                Connection connection = RabbitMQUtils.getConnection();
                // 获取通道
                Channel channel = connection.createChannel();
        ) {
            // 设置持久化标识为true即可
            boolean durable = true;
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
            // 保证一次只分发一条消息
            int prefetchCount = 1;
            channel.basicQos(prefetchCount);

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
