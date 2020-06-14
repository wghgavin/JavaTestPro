package com.gavin.basicLearning.SortingLearning;

import com.gavin.basicLearning.SortingLearning.Annotation.SortMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SortingProxyHandle<T> implements InvocationHandler {
    T target;
    public SortingProxyHandle(T target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class clazz =target.getClass();
        String sortName ="未知排序";
        //拿到注解
        if(clazz.isAnnotationPresent(SortMethod.class)){
            SortMethod annotation = (SortMethod)clazz.getAnnotation(SortMethod.class);
            sortName = annotation.name();
        }
        long start = System.currentTimeMillis();
        System.out.println(sortName+"正在执行");
        Object result = method.invoke(target,args);
        long end=System.currentTimeMillis();
        System.out.println(sortName + "耗时" + (end - start) + "ms");
        return  result;
    }
}
