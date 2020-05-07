package com.gavin.basicTest.ProxyTest.Impl;

import com.gavin.basicTest.ProxyTest.IMyProxyTarget;

public class MyProxyTargetImpl implements IMyProxyTarget {
    @Override
    public void insert() {
        System.out.println("业务插入逻辑方法");
    }

    @Override
    public void update() {
        System.out.println("业务更新方法执行");
    }
}
