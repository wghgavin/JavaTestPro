package com.gavin.basicLearning.DataStuctureLearning.HashTabTest;

/**
 * 表示雇员的链表
 */
public class EmpLinkedList {
    Emp head;
    /**
     * 增加结点
     *
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 遍历所有结点信息
     */
    public void printList(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表没有值");
            return;
        }
        System.out.println("第" + (no + 1) + "链表值为:" );
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.printf("id=%d name=%s\t", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        }
        System.out.println();
    }
    public Emp findEmpyById(int id){
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp =head;
        while (true){
            if(curEmp.id==id){
                return curEmp;
            }
            if(curEmp.next==null){
                return null;
            }
            curEmp=curEmp.next;
        }
    }
}
