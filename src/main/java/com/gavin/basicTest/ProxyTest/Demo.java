package com.gavin.basicTest.ProxyTest;

import com.gavin.basicTest.ProxyTest.Impl.MyProxyTargetImpl;

import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo {
    public static void main(String[] args) {
        MyProxyTargetImpl target = new MyProxyTargetImpl();
        ClassLoader loader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        Handle h = new Handle(target);
        IMyProxyTarget instance = (IMyProxyTarget) Proxy.newProxyInstance(loader,interfaces,h);
        instance.insert();
        try {
            InetAddress host = InetAddress.getLocalHost();
            String hostName = host.getHostName();
            String hostAddress = host.getHostAddress();
            System.out.println(hostName);
            System.out.println(hostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
