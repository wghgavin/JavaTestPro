package com.gavin.basicLearning.MutiThreadLearning.ThreadContainerTest;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 插数据的效率比较低，读的效率高
 */
public class ConcurrentHashMapTest {
    static Map<UUID,UUID> map = new ConcurrentHashMap<>();
    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT  =Constants.THREAD_COUNT;
    static {
        for(int i=0;i<count;i++){
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }
    static class MyThread extends Thread{
        int start;
        int gap = count/THREAD_COUNT;
        public MyThread(int start){
            this.start=start;
        }

        @Override
        public void run() {
            for(int i=start;i<start+gap;i++){
                map.put(keys[i],values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads =new Thread[THREAD_COUNT];
        for(int i=0;i<threads.length;i++){
            threads[i] =
                    new MyThread(i*(count/THREAD_COUNT));
        }
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end =System.currentTimeMillis();
        System.out.println("插入耗时"+(end-start)+"ms");
        System.out.println(map.size());
        start=System.currentTimeMillis();
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                for(int j=0;j<10000000;j++){
                    map.get(keys[10]);
                }
            });
        }
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end=System.currentTimeMillis();
        System.out.println("获取耗时"+(end-start)+"ms");
    }
}
