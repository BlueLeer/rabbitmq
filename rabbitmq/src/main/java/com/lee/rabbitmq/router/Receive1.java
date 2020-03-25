package com.lee.rabbitmq.router;

import com.lee.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLe
 * @date 2019/10/28 16:12
 * @description 删除商品消息 消费者
 */
public class Receive1 {
    public static final String EXCHANGE_NAME = "ps_exchange_2";
    public static final String QUEUE_NAME_1 = "router_queue_name_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_1, false, false, false, null);
        // 绑定队列到交换机,交换机的类型为默认的Direct交换机,它的路由模式是:根据routingKey匹配到对应的队列,然后将消息发送到队列中去
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, "delete");
        // 同一时刻,发送者只会发送一条消息到消费者
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.printf("[receive delete] %s \n", msg);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 手动确认消息【参数说明，参数1：该消息的index；参数2：是否批量应答，true：批量确认消息index的消息】
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        // 开启手动应答模式(可以理解为关闭了自动应答模式,默认也是false)
        // 消费方设置监听器的时候设置是否自动应答
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME_1, autoAck, consumer);

    }
}
