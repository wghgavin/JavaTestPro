package com.gavin.basicTest.LookUpAlgorithmLearning;

import com.gavin.basicTest.LookUpAlgorithmLearning.Annotation.LookupMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LookupProxyHandle<T> implements InvocationHandler {
    T target;
    public LookupProxyHandle(T target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> clazz = target.getClass();
        String methodName = "未知查找法";
        //拿到注解
        if(clazz.isAnnotationPresent(LookupMethod.class)){
            LookupMethod annotation = (LookupMethod)clazz.getAnnotation(LookupMethod.class);
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
