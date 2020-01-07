package com.lee.rabbitmq.tx;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/29 12:03
 * @description RabbitMQ的事务之 通过AMQP事务机制实现,这也是AMQP协议层面提供的解决方案
 * RabbitMQ中与事务相关的方法一共有3个:
 * 1. txSelect(); 用于将当前的channel设置成事务模式
 * 2. txCommit(); 用于提交事务
 * 3. txRollback(); 用于回滚事务
 *
 * 事务模式的性能很低,非事务模式的性能甚至比事务模式的性能高出百倍
 *
 * 不仅发送者能够使用事务来确认事务是否发送成功,消费者也能够使用事务
 *
 * 消费者使用事务:
 *
 * 假设消费者模式中使用了事务，并且在消息确认之后进行了事务回滚，那么RabbitMQ会产生什么样的变化？
 *
 * 结果分为两种情况：
 *
 * 1. autoAck=false手动应答的时候是支持事务的，也就是说即使你已经手动确认了消息已经收到了，但是确认消息会等事务的返回结果之后，
 *    在做决定是确认消息还是重新放回队列，如果你手动确认消息之后，又回滚了事务，那么以事务回滚为主，此条消息会重新放回队列；
 * 2. autoAck=true 自动应答为true的情况是不支持事务的，也就是说你即使在收到消息之后在回滚事务也是于事无补的，队列已经把消息移除了；
 *
 * 链接：https://www.jianshu.com/p/5e5871c2c205
 * 来源：简书
 */
public class Send {
    public static final String QUEUE_NAME = "tx_queue_name_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        try (
                Connection connection = RabbitMQUtils.getConnection();
                Channel channel = connection.createChannel()
        ) {
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            try {
                // 开启事务
                channel.txSelect();
                String msg = "hello tx!";
                channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
                int i = 1 / 0;
                channel.txCommit();
            } catch (Exception e) {
                channel.txRollback();
                System.out.println("[send tx] 出现错误!");
            }

        }


    }
}
