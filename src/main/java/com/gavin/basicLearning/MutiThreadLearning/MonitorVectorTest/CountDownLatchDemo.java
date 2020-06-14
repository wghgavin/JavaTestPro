package com.gavin.basicLearning.MutiThreadLearning.MonitorVectorTest;

import java.util.concurrent.CountDownLatch;
@SuppressWarnings("all")
public class CountDownLatchDemo {
    volatile MyVector vector = new MyVector();
    CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        CountDownLatchDemo demo = new CountDownLatchDemo();
        demo.start();
    }
    public void start(){
        new Thread(()->{
            System.out.println("线程"+Thread.currentThread().getName()+"开始执行");
            if(vector.size()!=5){
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("当前vector数达到5,线程"+Thread.currentThread().getName()+"结束");
            latch.countDown();
        },"t1").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                int num=i+1;
                vector.add(num);
                System.out.println(num);
                if(vector.size()==5){
                    latch.countDown();
                    try {
                        //Thread.sleep(2);
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t2").start();
    }
}
