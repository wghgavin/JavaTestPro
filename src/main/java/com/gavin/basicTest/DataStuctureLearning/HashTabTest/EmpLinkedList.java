package com.gavin.basicTest.DataStuctureLearning.HashTabTest;

/**
 * 表示雇员的链表
 */
public class EmpLinkedList {
    Emp head;

    /**
     * 增加结点
     * @param emp
     */
    public void add(Emp emp){
        if(head==null){
            head=emp;
            return;
        }
        Emp curEmp=head;
        while (curEmp.next!=null){
            curEmp=curEmp.next;
        }
        curEmp.next=emp;
    }

    /**
     * 遍历所有结点信息
     */
    public void printList(){
        if(head==null) System.out.println("当前链表无数值");
        Emp curEmp=head;
        while (curEmp!=null){
            System.out.printf("id=%d name=%s\t",curEmp.id,curEmp.name);
            curEmp=curEmp.next;
        }
        System.out.println();
    }
}
