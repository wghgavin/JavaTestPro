package com.gavin.basicLearning.MutiThreadLearning.synchronizedContainerTest;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition避免notify和wait的弊端
 */
public class LockContainer<T> {
    private Lock lock = new ReentrantLock();
    private Condition producer =lock.newCondition();
    private Condition consumer =lock.newCondition();
    final private LinkedList<T> lists = new LinkedList<>();
    private volatile int count=0;
    final private int MAX=10;
    public void put(T obj){
            try{
                lock.lock();
                while (count==MAX){
                    System.out.println(Thread.currentThread().getName()+":队列满了");
                    producer.await();
                }
                lists.add(obj);
                System.out.println(Thread.currentThread().getName()+"放入元素:"+obj);
                count++;
                consumer.signalAll();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
    }
    public T get(){
        T t=null;
        try{
               lock.lock();
               while (count==0){
                   System.out.println(Thread.currentThread().getName()+":队列空了");
                   consumer.await();
               }
               t= lists.removeFirst();
               System.out.println(Thread.currentThread().getName()+"拿走元素:"+t);
               count--;
               producer.signalAll();
           }
           catch (Exception e){
               e.printStackTrace();
               return null;
           }
           finally {
                lock.unlock();
           }
        return  t;
    }
    public int getCount(){
        try{
            lock.lock();
            return lists.size();
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        finally {
            lock.unlock();
        }
    }

}
