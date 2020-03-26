package com.lee.spring.rabbitmq.service;

import com.lee.spring.rabbitmq.model.OrderMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author lee
 * @date 2020/3/26 11:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMessageSendServiceTest {

    @Autowired
    private OrderMessageSendService orderMessageSendService;

    @Test
    public void sendOrderDeleteMsg() {
        Stream.of(1L, 2L, 3L, 4L, 5L).forEach(i -> {
            orderMessageSendService.sendOrderDeleteMsg(new OrderMsg(i, "我是订单" + i, "delete"));
        });
    }

    @Test
    public void sendOrderAddMsg() {
        Stream.of(1L, 2L, 3L, 4L, 5L).forEach(i -> {
            orderMessageSendService.sendOrderDeleteMsg(new OrderMsg(i*10, "我是订单" + i, "add"));
        });
    }
}