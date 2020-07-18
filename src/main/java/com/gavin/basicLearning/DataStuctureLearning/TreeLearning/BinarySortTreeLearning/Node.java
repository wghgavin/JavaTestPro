package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.BinarySortTreeLearning;

public class Node {
    int value;
    Node left;
    Node right;
    Node parent;
    public Node(int value) {
        this.value = value;
    }

    public void add(Node node) {
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
                node.parent=this;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
                node.parent=this;
            } else {
                this.right.add(node);
            }
        }
    }
    public void midList(){
        if(this.left!=null){
            this.left.midList();
        }
        System.out.println(this.value);
        if(this.right!=null){
            this.right.midList();
        }
    }
    public Node search(int value){
        if(this.value==value){
            return this;
        }
        else if(value<this.value) {
            return this.left.search(value);
        }
        else {
            return this.right.search(value);
        }
    }
}
