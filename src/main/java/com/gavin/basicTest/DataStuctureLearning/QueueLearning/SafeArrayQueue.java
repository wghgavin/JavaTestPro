package com.gavin.basicTest.DataStuctureLearning.QueueLearning;

/**
 * 线程安全队列
 */
public class SafeArrayQueue {
    public Object[] _queue;
    private int rear=0;//表示队列的尾巴
    private int front = 0;//表示队列的开头
    private Object synObj = new Object();
    public SafeArrayQueue(int size){
        _queue = new Object[size];
    }
    public SafeArrayQueue(){
        _queue = new Object[200];
    }
    private boolean isEmpty(){
        synchronized (synObj){
            return rear==front;
        }
    }
    private boolean isFull(){
        if((rear+1)%_queue.length==front) return true;
        return false;
    }
    public void add(Object o){
        synchronized (synObj){
            if(isFull()){
                try {
                    System.out.println("满了，放不进去");
                    synObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            rear++;
            if(rear==_queue.length) rear=0;
            _queue[rear]=o;
            synObj.notify();
        }
    }
    public Object take(){
        synchronized (synObj) {
            if(isEmpty()){
                try {
                    System.out.println("空了，拿不了");
                    synObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            front++;
            if(front==_queue.length)front=0;
            Object obj = _queue[front];
            _queue[front]=null;
            synObj.notify();
            return obj;
        }
    }
}
