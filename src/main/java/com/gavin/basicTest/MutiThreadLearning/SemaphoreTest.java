package com.gavin.basicTest.MutiThreadLearning;

import java.util.concurrent.Semaphore;
@SuppressWarnings("all")
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(1,true);
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T1 running....");
                Thread.sleep(1000);
                System.out.println("T1 running....");
            }
            catch (Exception ex){
               ex.printStackTrace();
            }
            finally {
                semaphore.release();
            }
        }).start();
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T2 running....");
                Thread.sleep(1000);
                System.out.println("T2 running....");
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            finally {
                semaphore.release();
            }
        }).start();
    }
}
