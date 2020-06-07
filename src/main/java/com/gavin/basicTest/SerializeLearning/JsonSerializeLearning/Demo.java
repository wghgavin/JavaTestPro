package com.gavin.basicTest.SerializeLearning.JsonSerializeLearning;

import com.alibaba.fastjson.JSON;
import com.gavin.basicTest.SerializeLearning.JsonSerializeLearning.bean.User;

public class Demo {
    public static void main(String[] args) {
        User user = new User(1,"wgh","kmh");
        String json = JSON.toJSONString(user);
        System.out.println(json);
    }
}
