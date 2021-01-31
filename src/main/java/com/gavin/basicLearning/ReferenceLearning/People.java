package com.gavin.basicLearning.ReferenceLearning;

/**
 * people实例
 */
public class People {
    public String name;
    public int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "[name:" + name + ",age:" + age + "]";

    }
}
