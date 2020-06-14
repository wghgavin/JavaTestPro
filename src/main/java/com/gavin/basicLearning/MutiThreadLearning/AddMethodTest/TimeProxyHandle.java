package com.gavin.basicLearning.MutiThreadLearning.AddMethodTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeProxyHandle<T> implements InvocationHandler {
    private T target;

    public TimeProxyHandle(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object result= method.invoke(target,args);
        long end = System.currentTimeMillis();
        String methodName = method.getName();
        System.out.println("当前方法为:"+methodName+"耗时"+(end-start)+"ms");
        return  result;
    }
}
