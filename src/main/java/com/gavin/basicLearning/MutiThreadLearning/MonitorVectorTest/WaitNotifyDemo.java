package com.gavin.basicLearning.MutiThreadLearning.MonitorVectorTest;


public class WaitNotifyDemo {
    MyVector vector = new MyVector();
    final Object synObj = new Object();
    public static void main(String[] args) {
        new WaitNotifyDemo().start();
    }
    public void start(){
       Thread t1 = new Thread(()->{
           synchronized (synObj){
               for(int i=0;i<10;i++){
                   vector.add(i);
                   System.out.println(i);
                   if(vector.size()==5){
                       synObj.notify();//不会释放
                       try {
                           synObj.wait();//所以下面要释放掉
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
        },"t1");
       Thread t2 = new Thread(()->{
           synchronized (synObj) {
                       try {
                           System.out.println("线程2开启");
                           synObj.wait();
                           System.out.println("已达到5个元素");
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
               System.out.println("线程2结束");
               synObj.notify();
           }
       },"t2");
        t2.start();
       t1.start();
    }
}
