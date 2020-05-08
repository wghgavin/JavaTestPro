package com.gavin.basicTest.MutiThreadLearning;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁保证读的时候可以多并发,写的时候需要同步,
 * 要么先读，要么先写，不能同时读写
 */
public class ReadWriteLockTest {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static int value;
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    public static void main(String[] args) {
        ReadWriteLockTest pro = new ReadWriteLockTest();
        Runnable readRunnable=()->pro.read(readLock);
        Runnable writeRunnable=()->pro.write(writeLock,value);
        for(int j=0;j<2;j++) new Thread(writeRunnable).start();
        for(int i=0;i<18;i++) new Thread(readRunnable).start();
    }
    public void read(Lock lock){
        //模拟读操作
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Read Over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    public void write(Lock lock,int v){
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value=v;
            System.out.println("write over!");
        }
        catch (Exception e){
              e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
