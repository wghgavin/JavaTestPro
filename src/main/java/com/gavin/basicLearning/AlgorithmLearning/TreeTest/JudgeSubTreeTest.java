package com.gavin.basicLearning.AlgorithmLearning.TreeTest;


/**
 * 判断b是否是a的子树https://blog.csdn.net/feinifi/article/details/101039187
 *       a            b
 * ------------------------
 *       10
 *      /  \
 *     9    8          9
 *    / \             / \
 *   7   6           7   6
 * 输入[10,9,8,7,6],[9,7,6]返回true
 * 输入[10,9,8,7,6],[9,7,8]返回false
 */

public class JudgeSubTreeTest {
    public static class TreeNode{
        int data;
        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        TreeNode left;
        TreeNode right;

        public int getData() {
            return data;
        }
    }
    public static void main(String[] args) {
       int[] arrayA ={10,9,8,7,6};
       int[] arrayB = {9,7,6};
       TreeNode nodeA = createTreeNodeByArray(arrayA,0);
       TreeNode nodeB = createTreeNodeByArray(arrayB,0);
       boolean result = new JudgeSubTreeTest().isSubTree(nodeA,nodeB);
        System.out.println(result);
    }
    private static TreeNode createTreeNodeByArray(int[] arr,int index){
        TreeNode tn = null;
        if(index<arr.length){
            int value = arr[index];
            tn = new TreeNode(value);
            tn.left= createTreeNodeByArray(arr,index*2+1);
            tn.right=createTreeNodeByArray(arr,index*2+2);
        }
        return tn;
    }
    private boolean equals(TreeNode nodeA,TreeNode nodeB){
        if(nodeA==null&&nodeB==null){
            return true;
        }
        if(nodeA==null|| nodeB==null){
            return  false;
        }
        return nodeA.getData()==nodeB.getData()&&equals(nodeA.left,nodeB.left)&&equals(nodeA.right,nodeB.right);
    }
    private boolean isSubTree(TreeNode nodeA,TreeNode nodeB){
        if(nodeA==null){
            return false;
        }
        if(equals(nodeA,nodeB)){
            return true;
        }
        return isSubTree(nodeA.left,nodeB)||isSubTree(nodeA.right,nodeB);
    }
}
