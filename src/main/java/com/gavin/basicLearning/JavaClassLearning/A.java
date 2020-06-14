package com.gavin.basicLearning.JavaClassLearning;

public class A extends A_Father{
    public static final int max = 300;//调用类的静态成员(除了final常量)和静态方法 不会发生类的初始化

    static {
        System.out.println("静态初始化A");
    }
    public A(){
        System.out.println("创建A类的对象");
    }
}