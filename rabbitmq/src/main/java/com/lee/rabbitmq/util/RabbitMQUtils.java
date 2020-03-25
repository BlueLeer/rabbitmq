package com.lee.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/22 10:32
 * @description
 */
public class RabbitMQUtils {
    // RabbitMQ主机地址
    public static final String HOST = "122.51.109.246";
    // AMQP协议端口
    public static final int PORT = 5672;
    public static final String USER_NAME = "guest";
    public static final String PASSWORD = "guest";
    // virtual_host相当于一个数据库实例
    public static final String VIRTUAL_HOST = "/simple";

    public static final String QUEUE_NAME = "test_simple_queue";
    private static ConnectionFactory factory = new ConnectionFactory();

    static {
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USER_NAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        return factory.newConnection();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicPublish("",QUEUE_NAME,null,"Hello RabbitMQ".getBytes());
        channel.close();
        connection.close();
    }
}
