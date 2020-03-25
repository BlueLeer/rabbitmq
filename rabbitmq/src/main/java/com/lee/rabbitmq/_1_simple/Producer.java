package com.lee.rabbitmq._1_simple;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/22 11:16
 * @description 简单模拟发送消息
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 1. 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 2. 创建频道(通道)
        Channel channel = connection.createChannel();
        
        // 3. 声明一个队列
        // 3.0 队列名称
        String queueName = "wangdahong";
        // 3.1 发送的消息是否持久化 true(持久化)
        boolean durable = false;
        // 3.2 是否独占模式
        boolean exclusive = false;
        // 3.3 消费者断开队列以后是否删除队列
        boolean autoDelete = false;
        // 3.4 消息其他参数
        Map<String, Object> arguments = null;
        // 如果该队列不存在就会重新创建一个,如果已经存在了,就不会重新创建了
        channel.queueDeclare(queueName, durable, exclusive, autoDelete, arguments);
        // 4. 发送消息
        for (int i = 0; i < 100; i++) {
            String format = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            TimeUnit.SECONDS.sleep(1);
            channel.basicPublish("", RabbitMQUtils.QUEUE_NAME, null, (format + "Hello RabbitMQ"+i).getBytes());
        }
        channel.close();
        connection.close();
        
    }

    /**
     * 测试持久化
     *
     *
     * Rabbit队列和交换器有一个不可告人的秘密，就是默认情况下重启服务器会导致消息丢失，那么怎么保证Rabbit在重启的时候不丢失呢？答案就是消息持久化。
     *
     * 当你把消息发送到Rabbit服务器的时候，你需要选择你是否要进行持久化，但这并不能保证Rabbit能从崩溃中恢复，想要Rabbit消息能恢复必须满足3个条件：
     *
     * 1. 声明队列的时候durable设置为true，消息持久化，代码：channel.queueDeclare(x, true, false, false, null)，参数2设置为true持久化；
     * 2. 设置投递模式deliveryMode设置为2（持久），代码：channel.basicPublish(x, x, MessageProperties.PERSISTENT_TEXT_PLAIN,x)，参数3设置为存储纯文本到磁盘；
     * 3. 消息已经到达持久化交换器上；
     * 4. 消息已经到达持久化的队列；
     *
     * 持久化工作原理: Rabbit会将持久化的消息写入磁盘上的持久化日志文件,等消息被消费以后,Rabbit会将该条消息标识为等待垃圾回收
     *
     *
     *
     * 作者：王磊的博客
     * 链接：https://www.jianshu.com/p/67e18c67b729</a>
     * 来源：简书
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    private static void test2() throws IOException, TimeoutException, InterruptedException {
        // 1. 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 2. 创建频道(通道)
        Channel channel = connection.createChannel();
        // 3. 声明一个队列
        // 3.1 发送的消息是否持久化 true(持久化)
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        Map<String, Object> arguments = null;
        // 如果该队列不存在就会重新创建一个,如果已经存在了,就不会重新创建了
        channel.queueDeclare(RabbitMQUtils.QUEUE_NAME, durable, exclusive, autoDelete, arguments);
        // 4. 发送消息
        String format = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        TimeUnit.SECONDS.sleep(1);
        // 发送消息的时候:设置投递模式为MessageProperties.PERSISTENT_TEXT_PLAIN,也就是持久化模式
        channel.basicPublish("", RabbitMQUtils.QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, format.getBytes());
        channel.close();
        connection.close();
    }
}
