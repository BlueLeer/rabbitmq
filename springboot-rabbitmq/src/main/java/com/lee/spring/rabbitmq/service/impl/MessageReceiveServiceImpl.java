package com.lee.spring.rabbitmq.service.impl;

import com.lee.spring.rabbitmq.service.MessageReceiveService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 简单队列消费者消费消息
 *
 * @author lee
 * @date 2020/3/26 0:28
 */
@Component
public class MessageReceiveServiceImpl implements MessageReceiveService {
    /**
     * 因为我再application.yml配置中配置了简单队列(simple)消息的ack模式为手动应答,这里没有返回值,相当于没有应答,因此:
     * 消息虽然被消费了,但是RabbitMQ会以为你没有收到消息,或者收到了但是正在处理一个耗时很长的业务,因此服务器上队列中该条
     * 消息并不会被移除
     *
     * @param msg
     */
    @RabbitHandler
    @Override
    @RabbitListener(queues = "queue_timer")
    public void process(String msg) {
        System.out.println("我接收到消息了##########" + msg);
    }

    /**
     * 处理完成以后,有个返回值,也就是说消费方把处理结果返回给了发送方,注意:这个ack应答不一样,这里相当于异步阻塞调用了
     *
     * @param msg
     * @return 处理结果(返回一个随机的6位数字)
     */
    @RabbitListener(queues = "queue_date")
    @RabbitHandler
    @Override
    public String processWithResp(String msg) {
        System.out.println("消费者受到消息了##########" + msg);
        // 睡眠3秒,模拟处理消息需要耗费时间
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int num = (int) (Math.random() * 1000000);
        return String.valueOf(num);
    }
}
