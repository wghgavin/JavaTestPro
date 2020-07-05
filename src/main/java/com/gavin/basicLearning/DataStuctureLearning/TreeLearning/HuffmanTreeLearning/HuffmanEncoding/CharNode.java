package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.HuffmanTreeLearning.HuffmanEncoding;

public class CharNode implements Comparable<CharNode>{
    public int count;
    public Byte data;
    public CharNode left;
    public CharNode right;

    public CharNode(byte data,int count){
       this.data=data;
       this.count=count;
    }
    public CharNode(Byte data,int count,CharNode left,CharNode right){
        this.data=data;
        this.count=count;
        this.left=left;
        this.right=right;
    }
    @Override
    public String toString() {
        return "[data:" +data+",count:"+count+"]";
    }
    public void prelist(){
        System.out.println(this);
        if(left!=null){
            left.prelist();
        }
        if(right!=null){
            right.prelist();
        }
    }
    @Override
    public int compareTo(CharNode o) {
        return this.count-o.count;
    }
}
