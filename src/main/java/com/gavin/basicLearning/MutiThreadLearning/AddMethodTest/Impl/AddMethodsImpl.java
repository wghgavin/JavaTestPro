package com.gavin.basicLearning.MutiThreadLearning.AddMethodTest.Impl;

import com.gavin.basicLearning.MutiThreadLearning.AddMethodTest.IAddMethods;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AddMethodsImpl implements IAddMethods {
    long count1 =0;
    AtomicLong count2=new AtomicLong(0);
    LongAdder count3 = new LongAdder();
    Object obj = new Object();
    private void ExcuteAddMethod(int type){
        Thread[] ts =new Thread[1000];
        for(int i=0;i<ts.length;i++){
            ts[i]=new Thread(()->{
                for(int k=0;k<100000;k++) {
                    switch (type){
                        case 1:
                            synchronized (obj) {
                                count1++;
                            }
                            break;
                        case 2:
                            count2.incrementAndGet();
                            break;
                        case 3:
                            count3.increment();
                            break;
                    }
                }
            });
        }
        for (Thread t:ts
        ) {
            t.start();
        }
        for (Thread t:ts
        ) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void synchronizeAdd() {
        ExcuteAddMethod(1);
    }

    @Override
    public void AtomicLongAdd() {
        ExcuteAddMethod(2);
    }

    @Override
    public void LongAdderAdd() {
        ExcuteAddMethod(3);
    }
}
