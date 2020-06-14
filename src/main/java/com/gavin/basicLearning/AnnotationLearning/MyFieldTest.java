package com.gavin.basicLearning.AnnotationLearning;

import java.lang.reflect.Field;

public class MyFieldTest {
    @MyField(description = "用户名")
    private String userName;

    public static void main(String[] args) {
        Class<MyFieldTest> c = MyFieldTest.class;
        for(Field f:c.getDeclaredFields()){
            if(f.isAnnotationPresent(MyField.class)){
                MyField annotation = f.getAnnotation(MyField.class);
                System.out.println("字段:"+f.getName()+" "+"描述:"
                        +annotation.description()+" "+"长度:"+annotation.length());
            }
        }
    }
}