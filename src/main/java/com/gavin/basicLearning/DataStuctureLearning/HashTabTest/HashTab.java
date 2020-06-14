package com.gavin.basicLearning.DataStuctureLearning.HashTabTest;

/**
 * 这是哈希表的类，管理多条链表
 */
public class HashTab {
    EmpLinkedList[] empLinkedListArray;
    int size;
    public HashTab(int size){
        this.size=size;
        empLinkedListArray = new EmpLinkedList[size];
    }
    public void add(Emp emp){
        int hashVal = hashFun(emp.id);
        empLinkedListArray[hashVal].add(emp);
    }

    /**
     * 遍历整个哈希表
     */
    public void printList(){
        for (EmpLinkedList linkedList:empLinkedListArray
             ) {
            linkedList.printList();
        }
    }
    //自定义哈希值
    public int hashFun(int id){
        return id%size;
    }
}
