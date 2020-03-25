package com.lee.spring.rabbitmq.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author lee
 * @date 2020/3/26 0:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSendServiceTest {
    @Autowired
    private MessageSendService messageSendService;

    @Test
    public void sendMsg() {
        messageSendService.sendMsg("sendMsg: hello");
        messageSendService.sendMsgWithResp("sendMsgWithResp: hello");
    }

}