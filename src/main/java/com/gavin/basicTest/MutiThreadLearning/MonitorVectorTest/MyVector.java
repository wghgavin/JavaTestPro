package com.gavin.basicTest.MutiThreadLearning.MonitorVectorTest;

import java.util.Vector;
/**
 * 淘宝面试题:
 * 实现一个容器，提供两个方法add,size,写两个线程
 * 线程1:添加10个元素到容器
 * 实时监控元素个数,当个数到达5个，线程2给出提示并结束
 */
public class MyVector {
    Vector<Object> vector;
    public MyVector(){
        vector = new Vector<>();
    }
    public void add(Object obj){
        vector.add(obj);
    }
    public int size(){
        return vector.size();
    }
}
