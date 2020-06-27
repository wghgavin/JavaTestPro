package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.BinaryTreeLearning;

/**
 * 二叉树
 */
public class BinaryTree {
    Node root;
    public void setRoot(Node root){
        this.root=root;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if(this.root!=null){
            this.root.listPreOrder();
        }
        else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder(){
        if(this.root!=null){
            this.root.listMidOrder();
        }
        else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if(this.root!=null){
            this.root.listPostOrder();
        }
        else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 增加节点
     * @param node
     */
    public void add(Node node){
      if(root==null) root=node;
      else {
          root.add(node);
      }
    }
}
