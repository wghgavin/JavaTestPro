package com.gavin.basicTest.JavaClassLearning;

public class Demo {
    public static void main(String[] args) {
        //主动引用
        //new A();
        //被动引用 final常量
        System.out.println(A.max);
        //反射 主动引用
        try {
            Class.forName("com.gavin.basicTest.JavaClassLearning.A").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过数组定义类引用，不会触发此类的初始化 被动引用
        A[]  as = new A[10];
        //当访问一个静态域时，只有真正声明这个域的类才会被初始化
        // 通过子类引用父类的静态变量，不会导致子类初始化
        System.out.println(B.max);//B不会初始化
    }
}