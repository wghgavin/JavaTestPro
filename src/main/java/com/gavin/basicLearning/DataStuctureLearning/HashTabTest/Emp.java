package com.gavin.basicLearning.DataStuctureLearning.HashTabTest;

/**
 * 表示一个雇员(一个Node节点)
 */
public class Emp {
    public int id;
    public String name;
    public Emp next;
    public Emp(int id,String name){
        this.id=id;
        this.name=name;
    }
}
