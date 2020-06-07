package com.gavin.basicTest.DataStuctureLearning.StackLearning;

/**
 * 用数组写栈
 */
public class ArrayStack {
    Object[] stack;
    int maxSize=0;
    int top=-1;//栈顶
    public ArrayStack(int size){
        this.maxSize=size;
        stack = new Object[size];
    }
    public ArrayStack(){
        this.maxSize =10000;
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public boolean isFull(){
        return top==maxSize-1;
    }
    public void push(Object obj){
        if(isFull()) {
            System.out.println("栈满了，别放了");
            return;
        }
        top++;
        stack[top]=obj;
    }
    public Object pop(){
        if(isEmpty()){
            System.out.println("空栈，拿不出");
            return null;
        }
        Object obj = stack[top];
        stack[top] =null;
        top--;
        return obj;
    }
}