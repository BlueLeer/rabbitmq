package com.lee.rabbitmq.tx_confirm;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者将信道设置成 confirm 模式，一旦信道进入 confirm 模式，所有在该信道上面发布的消息都会被指派一个唯
 * 一的 ID(从 1 开始)，一旦消息被投递到所有匹配的队列之后，broker 就会发送一个确认给生产者（包含消息的唯一
 * ID）,这就使得生产者知道消息已经正确到达目的队列了，如果消息和队列是可持久化的，那么确认消息会将消息写
 * 入磁盘之后发出，broker 回传给生产者的确认消息中 deliver-tag 域包含了确认消息的序列号，此外 broker 也可以设
 * 置 basic.ack 的 multiple 域，表示到这个序列号之前的所有消息都已经得到了处理。
 * 
 * confirm 模式最大的好处在于他是异步的，一旦发布一条消息，生产者应用程序就可以在等信道返回确认的同时继
 * 续发送下一条消息，当消息最终得到确认之后，生产者应用便可以通过回调方法来处理该确认消息，如果
 * RabbitMQ 因为自身内部错误导致消息丢失，就会发送一条 nack 消息，生产者应用程序同样可以在回调方法中处
 * 理该 nack 消息。
 *
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

        // 等所有的消息发送成功以后才会执行后面的代码,只要有一条消息没有发送确认,就会抛出异常
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
