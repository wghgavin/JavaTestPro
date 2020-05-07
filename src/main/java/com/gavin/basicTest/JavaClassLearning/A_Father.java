package com.gavin.basicTest.JavaClassLearning;

public class A_Father {
    static {
        System.out.println("初始化父类A_Father");//当初始化一个类，如果其父类没有被初始化，则先会初始化他的父类
    }
}
