package com.lee.rabbitmq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lee.rabbitmq.temp.ResvInfoDetailResponse;

/**
 * @author WangLe
 * @date 2019/11/1 14:44
 * @description
 */
public class Main {
    public static void main(String[] args) {
        ResvInfoDetailResponse response = new ResvInfoDetailResponse();
        String s = JSON.toJSONString(response, SerializerFeature.WriteMapNullValue);
        System.out.println(s);
    }
}
