package com.gavin.basicTest.MutiThreadLearning.ThreadPoolTest;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPool
 * 创建一个线程池，如果线程池中的线程数量过大，它可以有效的回收多余的线程，如果线程数不足，那么它可以创建新的线程。
 * 线程参数
 * 内部用的队列SynchronousQueue:一个不缓存任务的阻塞队列，生产者放入一个任务必须等到消费者取出这个任务。也就是说新任务进来时，
 * 不会缓存，而是直接被调度执行该任务，如果没有可用线程，则创建新线程，如果线程数量达到maxPoolSize，则执行拒绝策略
 * 很像c#的线程安全队列
 * ————————————————
 *优点:如果当第二个任务开始，第一个任务已经执行结束，
 * 那么第二个任务会复用第一个任务创建的线程，并不会重新创建新的线程，提高了线程的复用率
 *缺点:这种方式虽然可以根据业务场景自动的扩展线程数来处理我们的业务，
 * 但是最多需要多少个线程同时处理缺是我们无法控制的
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            final int index =i;
            try {
                TimeUnit.SECONDS.sleep(1);
                executor.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//打印结果可看出，由始至终都是都一个线程完成
        }
    }
}
