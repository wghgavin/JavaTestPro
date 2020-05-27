package com.gavin.basicTest.SortingLearning.SortImpl;

import com.gavin.basicTest.SortingLearning.Annotation.SortMethod;
import com.gavin.basicTest.SortingLearning.ISorting;

/**
 * 快速排序是对冒泡排序的一种改进，基本思想是:通过一趟排序将要排序的数据数据分隔成独立的
 * 两部分，其中一部分数据比另外一部分所有数据都小，然后再按此方法对这两部分数据分别进行快速排序，整个
 * 排序过程可以递归进行，以此达到整个数据变成有序序列
 *https://blog.csdn.net/u014241071/article/details/81565148
 * 我们程序中的代码是以中间数为基数
 */
@SortMethod(name = "快速排序",id=6)
public class QuickSorting implements ISorting {
    @Override
    public void sort(int[] array) {

    }
    private void sort(int[] arr,int left,int right){
        int mid =arr[(left+right)/2];
        int i=left;
        int j=right;
        int temp =0;
        while (i<j){//左要小于右，等于就是相遇了
            while (arr[i]<mid&&i<j){//左边走的话遇到大于中间的数的才停止
                i++;
            }
            while (arr[j]>mid&&i<j){//右边走的话遇到小于中间的数才停止
                j--;
            }
            //交换
            temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        //跳出了圈子说明
    }
}
