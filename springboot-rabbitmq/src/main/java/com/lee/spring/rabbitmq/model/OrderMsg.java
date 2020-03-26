package com.lee.spring.rabbitmq.model;

import java.io.Serializable;

/**
 * 订单消息
 *
 * @author lee
 * @date 2020/3/26 10:58
 */
public class OrderMsg implements Serializable {
    private Long id;
    /**
     * 消息类型 add:新增 delete:删除
     */
    private String type;
    private String content;

    public OrderMsg() {
    }

    public OrderMsg(Long id, String content, String type) {
        this.id = id;
        this.type = type;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
