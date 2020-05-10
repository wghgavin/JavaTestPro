package com.gavin.basicTest.QueueLearning;
/**
* 约瑟夫问题
 **/
public class LinkedListQueue {
    public class Node{
        Node next=null;
        int num;
        public Node(int num){
            this.num=num;
        }
    }
    Node first=null;
    int size=0;
    public LinkedListQueue(int nums){
        size=nums;
        Node helpNode =null;
        for(int i=1;i<=nums;i++){
            if(i==1){//第一个数
                first = new Node(i);
                first.next=first;
                helpNode=first;
            }
            else
            {
                helpNode.next=new Node(i);
                helpNode=helpNode.next;
                helpNode.next=first;
            }
        }
    }
    public void showAll(){
        if(first==null) System.out.println("数组为空");
        Node helpNode =first;
        while (true){
            System.out.println(helpNode.num);
            if(helpNode.next==first) break;
            helpNode=helpNode.next;
        }
    }

    /**
     *
     * @param start 起始位置
     * @param count 报数
     * @param left 剩余数
     */
    public void takeNode(int start,int count,int left){
        int currentLeft =size;
        if(start>size||first==null||left>=size) {
            System.out.println("出错");
            return;
        }
        Node helpNode =first;
        for(int i=0;i<start-1;i++){
            first=helpNode;
            helpNode = helpNode.next;//指向开始的结点
        }
        while (true){
            if(currentLeft==left) break;
            for(int i=0;i<count-1;i++){
                first=helpNode;
                helpNode=helpNode.next;
            }
            //达到之后移除
            first.next=helpNode.next;
            currentLeft--;
            System.out.println("移除"+helpNode.num+","+"剩余"+currentLeft+"个");
            helpNode=first.next;
        }
    }
}