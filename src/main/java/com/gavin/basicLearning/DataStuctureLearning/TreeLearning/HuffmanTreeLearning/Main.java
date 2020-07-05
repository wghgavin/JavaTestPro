package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.HuffmanTreeLearning;

import java.sql.ClientInfoStatus;
import java.util.*;

/**
 * 最终产生的赫夫曼树:
 *    61
 *   /  \
 * 29   32
 *     / \
 *   13   19
 *       /  \
 *      8   11
 *         /  \
 *        4    7
 *       / \
 *      1   3
 */

/**
 *思路： 先排序
 * 取两个最小的生成一个父节点，一直重复，生成二叉树，最后就是赫夫曼树
 */
public class Main {
    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,1};
        Node parent = createHuffmanTree(arr);
        parent.prelist();
    }
    public static Node createHuffmanTree(int[] arr){
        /**
         * 1. 遍历arr数组
         * 2. 将arr的每个元素构成一个Node
         * 3.将Node放入到ArrayList中
         */
        List<Node> nodes = new LinkedList<>();
        for(int value:arr){
            nodes.add(new Node(value));
        }
        Node parent =null;
        while (nodes.size()>1){
            //排序 从小到大
            Collections.sort(nodes);
            //取出根节点最小的两颗二叉树
            //(1) 取出权值最小的节点(二叉树)
            Node left = nodes.get(0);
            //(2) 取出权值第二小结点(二叉树)
            Node right = nodes.get(1);
            //(3)构建一颗新二叉树
            parent =new Node(left.value+right.value,left,right);
            //(4)从list删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
//            Collections.sort(nodes);
//            System.out.println(nodes);
        }
        return parent;
    }
}

