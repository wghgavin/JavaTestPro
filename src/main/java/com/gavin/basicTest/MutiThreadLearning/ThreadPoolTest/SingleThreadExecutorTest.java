package com.gavin.basicTest.MutiThreadLearning.ThreadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newSingleThreadExecutor:　创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
 * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行\
 * 使用队列:LinkedBlockingQuene
 */
public class SingleThreadExecutorTest {
    public static void main(String[] args) {
          new SingleThreadExecutorTest().start();
    }
    public void start(){
      ExecutorService executor= Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "   " + index);
            });
        }
        executor.shutdown();
    }
}
