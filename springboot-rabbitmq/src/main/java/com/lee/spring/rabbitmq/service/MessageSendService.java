package com.lee.spring.rabbitmq.service;

/**
 * @author lee
 * @date 2020/3/25 23:38
 */
public interface MessageSendService {
    void sendMsg(String message);

    String sendMsgWithResult(Object message);
}
