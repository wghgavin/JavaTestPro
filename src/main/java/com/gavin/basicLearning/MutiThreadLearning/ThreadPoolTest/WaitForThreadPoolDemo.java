package com.gavin.basicLearning.MutiThreadLearning.ThreadPoolTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 等待线程池完成的例子
 */
public class WaitForThreadPoolDemo {
    public static void main(String[] args) {
        new WaitForThreadPoolDemo().FutureTaskDemo();
    }

    /**
     * 使用countDownLatch等待
     */
    public void countDownDemo(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        final CountDownLatch latch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            executor.execute(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    latch.countDown();
                }
            });
        }
        try{
            latch.await();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("正在关闭线程池");
        executor.shutdown();
        System.out.println("线程池关闭完成");
    }

    /**
     * 使用futureTask拿返回值
     */
    public void FutureTaskDemo(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for(int i=0;i<10;i++){
            Future<String> future =executor.submit(()->{
                System.out.println("当前线程为"+Thread.currentThread().getName());
                return Thread.currentThread().getName();
            });
            futures.add(future);
        }
        try{
            for(Future<String> future:futures){
                System.out.println("返回结果===="+future.get());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("线程池关闭完成");
    }
}
