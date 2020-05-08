package com.gavin.basicTest.MutiThreadLearning;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 一个栅栏，满了之后则则执行下一步
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20,()->{
            System.out.println("已满");
        });
        for(int i=0;i<30;i++){
            new Thread(()->{
                try {
                    barrier.await();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"Thread"+i).start();
        }
    }
}
