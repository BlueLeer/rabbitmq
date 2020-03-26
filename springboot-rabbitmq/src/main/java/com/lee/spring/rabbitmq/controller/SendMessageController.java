package com.lee.spring.rabbitmq.controller;

import com.lee.spring.rabbitmq.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lee
 * @date 2020/3/26 15:25
 */
@Controller
public class SendMessageController {
    @Autowired
    private MessageSendService messageSendService;

    @RequestMapping("/sendWithResult")
    @ResponseBody
    public String sendMessageWithResult(String msg) {
        String result = messageSendService.sendMsgWithResult(msg);
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒"));
        return time + "<" + result + ">";
    }
}
