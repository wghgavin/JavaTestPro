package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.HuffmanTreeLearning;

public class Node implements Comparable<Node>{
    public int value;
    public Node left;
    public Node right;

    public Node(int value){
        this.value=value;
    }
    public Node(int value,Node left,Node right){
        this.value=value;
        this.left=left;
        this.right=right;
    }
    @Override
    public String toString() {
        return value+"";
    }
    public void prelist(){
        System.out.println(value);
        if(left!=null){
            left.prelist();
        }
        if(right!=null){
            right.prelist();
        }
    }
    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }
}
