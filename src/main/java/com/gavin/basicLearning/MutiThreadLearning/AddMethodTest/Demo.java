package com.gavin.basicLearning.MutiThreadLearning.AddMethodTest;

import com.gavin.basicLearning.MutiThreadLearning.AddMethodTest.Impl.AddMethodsImpl;

import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {
        try {
            IAddMethods addMethod = new AddMethodsImpl();
            ClassLoader loader = addMethod.getClass().getClassLoader();
            Class<?>[] interfaces = addMethod.getClass().getInterfaces();
            TimeProxyHandle<IAddMethods> handle = new TimeProxyHandle<>(addMethod);
            IAddMethods instance = (IAddMethods) Proxy.newProxyInstance(loader,interfaces,handle);
            instance.synchronizeAdd();
            instance.AtomicLongAdd();
            instance.LongAdderAdd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
