package com.gavin.basicTest.MutiThreadLearning.ThreadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newFixedThreadPool
 * 这种方式可以指定线程池中的线程数。举个栗子，如
 * 果一间澡堂子最大只能容纳20个人同时洗澡，那么后面来的人只能在外面排队等待
 * 使用的队列:LinkedBlockingQuene(插入和拿取支持同时进行)
 * 基于链表的无界阻塞队列（其实最大容量为Interger.MAX），按照FIFO排序。由于该队列的近似无界性，当线程池中线程数量达到corePoolSize后，
 * 再有新任务进来，会一直存入该队列，而不会去创建新线程直到maxPoolSize，因此使用该工作队列时，参数maxPoolSize其实是不起作用的。
 * 优点:线程数可控
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  " + index);
            });
        }
        executor.shutdown();
    }
}
