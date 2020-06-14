package com.gavin.basicLearning.MutiThreadLearning;

import java.util.ArrayList;
import java.util.List;

public class VolatileTest {
    int count =0;
    synchronized void  m(){
        for(int i=0;i<10000;i++) count++;
    }
    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        List<Thread> threads = new ArrayList<>();
        for (int i=0;i<10;i++){
            threads.add(new Thread(()->{
                test.m();
            }));
        }
        threads.forEach(o->o.start());
        System.out.println(test.count);
    }
}
