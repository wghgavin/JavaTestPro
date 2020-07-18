package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.BinarySortTreeLearning;

/**
 *              7
 *            /   \
 *          3      10
 *         / \     / \
 *        1   5   9  12
 *
 */
public class Main {
    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree();
        int[] arr={7,3,10,12,5,1,9};
        for (int n:arr
             ) {
            tree.add(new Node(n));
        }
        tree.midList();
    }
}
