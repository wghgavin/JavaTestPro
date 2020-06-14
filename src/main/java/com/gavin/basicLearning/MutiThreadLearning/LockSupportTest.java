package com.gavin.basicLearning.MutiThreadLearning;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i=1;i<=10;i++){
                if(i==5){
                    LockSupport.park();
                }
                if(i==8){
                    LockSupport.park();
                }
                System.out.println("执行第"+i+"次");
                try {
                    TimeUnit.SECONDS.sleep(1);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        LockSupport.unpark(t);
    }
}
