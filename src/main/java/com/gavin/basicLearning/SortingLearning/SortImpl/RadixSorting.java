package com.gavin.basicLearning.SortingLearning.SortImpl;

import com.gavin.basicLearning.SortingLearning.Annotation.SortMethod;
import com.gavin.basicLearning.SortingLearning.ISorting;

import java.util.*;

/**
 * 思想:
 * 把每个数按位数分别放入从0到10的桶中，每放完一次就进行一次排序,排序完清空桶
 * (基数排序是典型的空间换时间排序算法)比归并快，并且稳定
 * 基数排序对海量数据排序时，容易造成OutOffMemoryError
 * 【时间复杂度】O(n*k) k指桶的个数
 * 空间复杂度O(N+K)
 */
@SortMethod(name = "基数排序", id = 8)
public class RadixSorting implements ISorting {
    @Override
    public void sort(int[] array) {
        int[][] bucket = new int[10][array.length];//篮子
        int[] order = new int[10];//保存每个组存多少个数
        int i = 0;
        int max = array[0];
        for (i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        int maxLength = (max + "").length();//最大位数
        int val = 0;//存放所整除的数
        int index = 1;//代表位数，从各位开始
        while (index < Math.pow(10, maxLength)) {
            for (i = 0; i < array.length; i++) {
                val = array[i] / index % 10;
                bucket[val][order[val]] = array[i];
                order[val] += 1;
            }
            int p = 0;
            for (i = 0; i < bucket.length; i++) {
                for(int j=0;j<order[i];j++){
                    array[p++]=bucket[i][j];
                }
                order[i]=0;
            }
            index *= 10;
        }
        //System.out.println(Arrays.toString(array));
    }
}
