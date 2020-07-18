package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.BinarySortTreeLearning;

/**
 * 二叉排序树
 */
public class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (this.root == null) {
            root = node;
        } else {
            this.root.add(node);
        }
    }

    public void midList() {
        if (root == null) {
            System.out.println("结点为空");
            return;
        }
        this.root.midList();
    }

    private Node search(int value) {
        return this.search(value);
    }

    /**
     * 思路:
     * 一：删除的是叶子节点
     * 1.是父节点的左子结点:
     * parent.left=null;
     * 2.是父节点的右子节点:
     * parent.right=null;
     * -----------------------------------
     * 二.删除的节点有一颗树:
     * 1.是父节点的左子节点
     * 1.1:该节点带左子树
     * parent.left=node.left;
     * 1.2:该节点带右子树
     * parent.left=node.rigt
     * 2.是父节点的右子节点
     * 2.1:该节点带左子树:
     * parent.right=node.left
     * 2.2该节点带右子树:
     * parent.right=node.right;
     * -----------------------------------
     * 三.删除结点带两颗树:
     * 1.往右结点找到最小值的结点
     * 2.新建一个临时变量int = 最小结点的值
     * 3.删除最小结点
     * 4.当前结点的值赋值为临时变量
     *
     * @param value
     */
    public void delete(int value) {
        if (root == null) {
            System.out.println("结点为空");
            return;
        }
        Node node = search(value);
        //叶子节点
        if (node.left == null && node.right == null) {
            Node parent = node.parent;
            if(parent.left==node){
                parent.left=null;
            }
            else {
                parent.right=null;
            }
        }
        //单子树，为左结点
        else if (node.left != null && node.right == null) {
           Node parent = node.parent;
           if(parent.left==node){
               parent.left=node.left;
           }
           else {
               parent.right=node.left;
           }
        }
        //单子树，为右结点
        else if (node.left == null && node.right != null) {
            Node parent = node.parent;
            if(parent.left==node){
                parent.left=node.right;
            }
            else {
                parent.right=node.right;
            }
        }
        //双子树
        else {
            Node parent = node.parent;

        }
    }
    private int getMin(Node node){
        if(node.left==null){
           return node.value;
        }
        else {
            return getMin(node.left);
        }

    }
}
