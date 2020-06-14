package com.gavin.basicLearning.MutiThreadLearning.ThreadPoolTest;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * newScheduledThreadPool:
 * 该线程池支持定时，以及周期性的任务执行，我们可以延迟任务的执行时间，
 * 也可以设置一个周期性的时间让任务重复执行。 该线程池中有以下两种延迟的方法。
 * 使用队列:DelayedWorkQueue(优先级队列):任务队列会根据任务延时时间的不同进行排序，
 * 延时时间越短地就排在队列的前面，先被获取执行
 *
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        new ScheduledThreadPoolTest().methodTwo();
    }

    /**
     * 该线程池支持定时，以及周期性的任务执行，
     * 我们可以延迟任务的执行时间，也可以设置一个周期性的时间让任务重复执行。
     * 该线程池中有以下两种延迟的方法。
     */
    //scheduleAtFixedRate,线程执行开始就重新计时
    private void methodOne(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.scheduleAtFixedRate(()->{
            long start = new Date().getTime();
            System.out.println(Thread.currentThread().getName() +"开始执行时间:" +
                    DateFormat.getTimeInstance().format(new Date()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = new Date().getTime();
            System.out.println(Thread.currentThread().getName() +"执行花费时间=" + (end - start) / 1000 + "m");
            System.out.println(Thread.currentThread().getName() +"执行完成时间：" + DateFormat.getTimeInstance().format(new Date()));
            System.out.println("======================================");
        },1,3, TimeUnit.SECONDS);
    }
    //scheduleWithFixedDelay，线程执行完才重新计时
    private void methodTwo(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.scheduleWithFixedDelay(()->{
            long start = new Date().getTime();
            System.out.println(Thread.currentThread().getName() +"开始执行时间:" +
                    DateFormat.getTimeInstance().format(new Date()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = new Date().getTime();
            System.out.println(Thread.currentThread().getName() +"执行花费时间=" + (end - start) / 1000 + "m");
            System.out.println(Thread.currentThread().getName() +"执行完成时间：" + DateFormat.getTimeInstance().format(new Date()));
            System.out.println("======================================");
        },1,3, TimeUnit.SECONDS);
    }
}
