package com.gavin.basicTest.SearchAlgorithmLearning;

import com.gavin.basicTest.SearchAlgorithmLearning.Annotation.SearchMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SearchProxyHandle<T> implements InvocationHandler {
    T target;
    public SearchProxyHandle(T target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> clazz = target.getClass();
        String methodName = "未知查找法";
        //拿到注解
        if(clazz.isAnnotationPresent(SearchMethod.class)){
            SearchMethod annotation = clazz.getAnnotation(SearchMethod.class);
            methodName = annotation.name();
        }
        long start = System.currentTimeMillis();
        System.out.println(methodName+"正在执行");
        Object result = method.invoke(target,args);
        long end=System.currentTimeMillis();
        System.out.println(methodName + "耗时" + (end - start) + "ms");
        return  result;
    }
}
