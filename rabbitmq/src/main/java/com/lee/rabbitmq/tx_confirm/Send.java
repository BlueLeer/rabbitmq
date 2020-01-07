package com.lee.rabbitmq.tx_confirm;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/29 12:16
 * @description 发送方确认模式 confirm
 */
public class Send {

    public static final String QUEUE_NAME_1 = "confirm_queue_1";
    public static final String QUEUE_NAME_2 = "confirm_queue_2";
    public static final String QUEUE_NAME_3 = "confirm_queue_3";

    public static void main(String[] args) throws IOException, TimeoutException {
//        test1();
//        test2();
        test3();
    }

    /**
     * 方式一：channel.waitForConfirms()普通发送方确认模式
     */
    public static void test1() throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_1, false, false, false, null);
        // 开启发送方确认模式
        channel.confirmSelect();
        String msg = "hello confirm 1";
        channel.basicPublish("", QUEUE_NAME_1, null, msg.getBytes());

        // 阻塞式的 等待消息被服务器确认即可
        try {
            if (channel.waitForConfirms()) {
                System.out.println("消息发送成功!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.close();
        connection.close();
    }

    /**
     * 批量confirm模式
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public static void test2() throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_2, false, false, false, null);

        // 开启发送确认模式
        channel.confirmSelect();
        for (int i = 0; i < 10; i++) {
            String msg = "hello " + i;
            channel.basicPublish("", QUEUE_NAME_2, null, msg.getBytes());
        }

        // 等所有的消息发送成功以后才会执行后面的代码,只要有一小消息没有发送确认,就会抛出异常
        try {
            channel.waitForConfirmsOrDie();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("有的消息发送没有成功!");
        }

        System.out.println("所有的消息都已经发送成功了!");

        channel.close();
        connection.close();
    }

    /**
     * 异步confirm模式
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public static void test3() throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_2, false, false, false, null);

        // 开启发送确认模式
        channel.confirmSelect();
        for (int i = 0; i < 10; i++) {
            String msg = "hello " + i;
            channel.basicPublish("", QUEUE_NAME_2, null, msg.getBytes());
        }

        // 异步监听确认和未确认的消息
        channel.addConfirmListener(new ConfirmListener() {
            // 已应答
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.printf("已确认消息, 标识: %d, 多个消息: %b", deliveryTag, multiple);
            }

            // 否定应答,未确认的消息
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.printf("未确认消息, 标识: %d, 多个消息: %b", deliveryTag, multiple);

            }
        });


//        channel.close();
//        connection.close();
    }


}
