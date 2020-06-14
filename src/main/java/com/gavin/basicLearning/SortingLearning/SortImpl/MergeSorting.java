package com.gavin.basicLearning.SortingLearning.SortImpl;

import com.gavin.basicLearning.SortingLearning.Annotation.SortMethod;
import com.gavin.basicLearning.SortingLearning.ISorting;

/**
 * 归并排序(分治的思想)
 * https://blog.csdn.net/qq_36442947/article/details/81612870
 * 最坏,最好，平均时间复杂度都是O(nlogn)
 * 【空间复杂度】O(n)
 */
@SortMethod(name = "归并排序", id = 7)
public class MergeSorting implements ISorting {
    @Override
    public void sort(int[] array) {
        divide(array, 0, array.length - 1, new int[array.length]);
    }
    //分
    private void divide(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            divide(array,left,mid,temp);//左边的
            divide(array,mid+1,right,temp);
            merge(array,left,mid,right,temp);
        }
    }
    //结合
    private void merge(int[] arr,int left,int mid,int right,int[] temp){
       int l = left;
       int r = mid+1;
       int p=left;
       while (l<=mid&&r<=right){
           if(arr[l]<=arr[r]){
               temp[p++]=arr[l++];
           }
           else {
               temp[p++]=arr[r++];
           }
       }
       while (l<=mid){//左边漏的
           temp[p++]=arr[l++];
       }
       while (r<=right){//右边漏的
           temp[p++]=arr[r++];
       }
       //把数组拷贝至原数组
        for(int i=left;i<=right;i++){
            arr[i]=temp[i];
        }
    }
}
