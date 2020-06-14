package com.gavin.basicLearning.ReferenceLearning;

import java.lang.ref.SoftReference;

/**
 * 软引用
 * 如果一个对象只具有软引用，那就类似于可有可物的生活用品。如果内存空间足够，
 * 垃圾回收器就不会回收它，
 * 如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，
 * 该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存。
 * 当内存足够大时可以把数组存入软引用，取数据时就可从内存里取数据，提高运行效率
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        System.out.println("开始");
        Integer i = Integer.valueOf(2);
        SoftReference<Integer> sr = new SoftReference<Integer>(i);
        i=null;
        if (sr!=null) {
            i=sr.get();
            System.out.println("sr不为空");
        }
        else {
            i=Integer.valueOf(3);
            sr=new SoftReference<Integer>(i);
            System.out.println("sr为空");
        }
        System.out.println("结束");
    }
}
