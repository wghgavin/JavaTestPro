package com.gavin.basicTest.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handle<T> implements InvocationHandler {
    private T target;
    public Handle(T proxy){
        target = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始执行"+method.getName()+"方法");
        long start = System.currentTimeMillis();
        Object result = method.invoke(target,args);
        Thread.sleep(500);
        long end = System.currentTimeMillis();
        System.out.println("结束执行"+method.getName()+"方法");
        System.out.println("耗时"+(end-start)+"毫秒");
        return  result;
    }
}
