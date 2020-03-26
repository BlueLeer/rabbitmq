package com.lee.spring.rabbitmq.service;

/**
 * @author lee
 * @date 2020/3/26 0:28
 */
public interface MessageReceiveService {
    void process(String msg);

    String processWithResp(String msg);
}
