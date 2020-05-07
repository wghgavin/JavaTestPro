package com.gavin.basicTest.MutiThreadLearning;

public class SingleInstanceTest {
    private static volatile SingleInstanceTest _instance;
    private SingleInstanceTest(){}
    public static SingleInstanceTest getInstance(){
        if(_instance==null){
            synchronized (SingleInstanceTest.class){
                if(_instance==null){
                    _instance = new SingleInstanceTest();
                }
            }
        }
        return _instance;
    }
}
