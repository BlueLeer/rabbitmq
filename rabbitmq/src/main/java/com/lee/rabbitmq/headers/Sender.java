package com.lee.rabbitmq.headers;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author lee
 * @date 2020/3/25 17:16
 */
public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("headers_exchange", BuiltinExchangeType.HEADERS);
        channel.basicPublish("headers_exchange","",null,"我是Headers模式".getBytes(StandardCharsets.UTF_8));
    }
}
