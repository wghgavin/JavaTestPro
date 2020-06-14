package com.gavin.basicLearning.MutiThreadLearning;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangeTest {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s ="T1";
            try{
                System.out.println(Thread.currentThread().getName()+":正在发出交换请求");
                s=exchanger.exchange(s);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+s);
        },"thread1").start();
        new Thread(()->{
            String s ="T2";
            try {
                 TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+":正在发出交换请求");
                 s=exchanger.exchange(s);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+s);
        },"thread2").start();

    }
}
