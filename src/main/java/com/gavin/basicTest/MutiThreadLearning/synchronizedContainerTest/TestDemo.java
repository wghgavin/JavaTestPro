package com.gavin.basicTest.MutiThreadLearning.synchronizedContainerTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 该例子有个弊端就是notify的是其他所有的线程包括生产者和消费者线程
 */
public class TestDemo {
    public static void main(String[] args) {
        LockContainer<Integer> container = new LockContainer();
        ExecutorService executor = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 2; i++) {
            executor.execute(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        Integer item = container.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
            executor.execute(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(4);
                        container.put(ThreadLocalRandom.current().nextInt());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

    }
}
