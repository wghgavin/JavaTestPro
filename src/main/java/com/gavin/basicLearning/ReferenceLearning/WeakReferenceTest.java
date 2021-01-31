package com.gavin.basicLearning.ReferenceLearning;

import java.lang.ref.WeakReference;

/**
弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象。在
 java中，用java.lang.ref.WeakReference类来表示。下面是使用示例

 第二个输出结果是null，这说明只要JVM进行垃圾回收，被弱引用关联的对象必定会被回收掉。
 不过要注意的是，这里所说的被弱引用关联的对象是指只有弱引用与之关联，如果存在强引用同时与之关联，
 则进行垃圾回收时也不会回收该对象（软引用也是如此）。
*/
public class WeakReferenceTest {
    public static void main(String[] args) {
        testWithoutStrongReference();
        testWithReferencce();
    }
    private static void testWithoutStrongReference(){
        WeakReference<Integer> weakReference = new WeakReference<>(new Integer(45));
        System.out.println(weakReference.get());
        System.gc();//通知GVM回收资源
        System.out.println(weakReference.get());
    }
    private static void testWithReferencce(){
        People people=new People("zhouqian",20);
        WeakReference<People>reference=new WeakReference<People>(people);//<span style="color:#FF0000;">关联强引用</span>
        System.out.println(reference.get());
        System.gc();
        System.out.println(reference.get());
    }
}
