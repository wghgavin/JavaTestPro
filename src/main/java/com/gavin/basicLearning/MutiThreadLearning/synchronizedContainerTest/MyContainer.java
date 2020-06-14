package com.gavin.basicLearning.MutiThreadLearning.synchronizedContainerTest;

import java.util.LinkedList;

/**
 * 淘宝面试题二
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法,能够
 * 支持2个生产者线程以及10个消费者线程的阻塞调用
 * 该例子有个弊端就是notify的是其他所有的线程包括生产者和消费者线程
 */
public class MyContainer<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T obj) {
        while (lists.size() == MAX) {
            try {
                System.out.println(Thread.currentThread().getName() + ":队列满了");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(obj);
        System.out.println(Thread.currentThread().getName() + ":放入数据");
        //唤醒其他线程
        this.notifyAll();
    }

    public synchronized T get() {
        while (lists.size() == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + ":队列空了");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T item = lists.removeFirst();
        System.out.println(Thread.currentThread().getName() + ":取出数据:" + item);
        this.notifyAll();
        return item;
    }

    public synchronized int getCount() {
        synchronized (this) {
            return lists.size();
        }
    }
}
