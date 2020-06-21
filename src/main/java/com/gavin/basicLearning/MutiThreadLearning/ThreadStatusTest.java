package com.gavin.basicLearning.MutiThreadLearning;

/**
 * java线程状态:
 * https://www.cnblogs.com/cowboys/p/9315331.html
 * 线程主要状态:
 * 1.初始(NEW):新创建一个线程对象:
 *   |-实现Runnable接口和继承Thread可以得到一个线程类，new一个实例出来，线程就进入了初始状态
 * 2.运行(RUNNABLE):Java线程中将就绪（ready）和运行中（running）两种状态笼统的成为“运行”,
 * 线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，
 * 获取cpu 的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得cpu 时间片后变为运行中状态（running）
 *   |-
 * 3.阻塞(BLOCKING):表线程阻塞于锁。
 * 4.等等(WAITING):进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
 * 5.终止(TERMINATED):表示线程已执行完毕
 */
public class ThreadStatusTest {
}
