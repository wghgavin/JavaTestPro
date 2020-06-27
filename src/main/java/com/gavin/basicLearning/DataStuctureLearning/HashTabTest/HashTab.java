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
        //new完之后需要初始化，因为里面元素是空的
        for(int i=0;i<size;i++){
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }
    public void add(Emp emp){
        int hashVal = hashFun(emp.id);
        empLinkedListArray[hashVal].add(emp);
    }

    /**
     * 遍历整个哈希表
     */
    public void printList(){
       for(int i=0;i<empLinkedListArray.length;i++){
           empLinkedListArray[i].printList(i);
       }
    }

    /**
     * 根据输入id查找雇员
     */
    public void findEmpByid(int id){
        int hashCode = hashFun(id);
        Emp emp = empLinkedListArray[hashCode].findEmpyById(id);
        if(emp!=null){
            System.out.println("找到第"+hashCode+"条链表中的雇员信息:\r\n"+emp.name+"\t"+emp.id);
        }
        else {
            System.out.println("无id为"+id+"的雇员信息");
        }
    }
    //自定义哈希值
    public int hashFun(int id){
        return id%size;
    }
}
