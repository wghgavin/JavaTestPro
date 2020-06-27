package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.BinaryTreeLearning;

import com.gavin.basicLearning.DataStuctureLearning.TreeLearning.BinaryTreeLearning.BinaryTree;

public class Node {
    public int id;
    public String name;
    public Node left;
    public Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + "'}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Node node){
        if(node.id<id){
            if(left==null) left=node;
            else add(node);
        }
        else {
            if (right==null)right=node;
            else add(node);
        }
    }

    /**
     * 前序遍历
     */
    public void listPreOrder() {
        System.out.println(this);//先输出父节点
        if (this.left != null) {
            this.left.listPreOrder();
        }
        if (this.right != null) {
            this.right.listPreOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void listMidOrder() {
        if (this.left != null) {
            this.left.listPreOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.listPreOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void listPostOrder() {
        if (this.left != null) {
            this.left.listPreOrder();
        }
        if (this.right != null) {
            this.right.listPreOrder();
        }
        System.out.println(this);//输出父节点
    }
}
