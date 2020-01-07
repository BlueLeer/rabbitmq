package com.lee.rabbitmq.ps;

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
 * @description publish/subscribe 模式
 * 1. 一个生产者多个消费者
 * 2. 每个消费者都有一个自己的队列
 * 3. 生产者不是将消息直接发送给消费者,而是把消息发送给了交换机(转发器)
 * 4. 每个队列都要绑定到交换机
 * 5. 生产者发送的消息经过交换机到达队列,队列再把消息发送给消费者
 *
 * 交换机类型: fanout: 不处理路由键.只需要将队列绑定到交换机上,发送消息到交换机都会被转发到与该交换机绑定的所有队列上
 *            direct:处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。如果一个队列
 *                   绑定到该交换机上要求路由键 “dog”，则只有被标记为“dog”的消息才被转发，不会转发 dog.puppy，也不会转发dog.guard，只会转发 dog。
 *            topic: 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配一个词。因此“audit.#”能够匹配到
 *                   “audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
 */
public class Send {
    public static final String EXCHANGE_NAME = "ps_exchange_1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        try (
                Connection connection = RabbitMQUtils.getConnection();
                Channel channel = connection.createChannel();
        ) {
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT); // fanout 分裂(不处理路由键)
            for (int i = 0; i < 10; i++) {
                String msg = "hello exchange" + i;
                channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
                System.out.printf("[send msg] %S \n", msg);
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
