package com.gavin.basicTest.SortingLearning.SortImpl;

import com.gavin.basicTest.SortingLearning.Annotation.SortMethod;
import com.gavin.basicTest.SortingLearning.ISorting;

import java.util.Arrays;

/**
 * 选择排序,从小到大【稳定】(O(n2))
 *
 * 思想:第一次从arr[0]~arr[n-1]选取最小值，与arr[0]交换,第二次从
 * arr[1]~arr[n-1]中选取最小值，与arr[1]交换,第三次....以此类推,
 * 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换
 * 总共交换n-1次
 * 比冒泡排序快,因为交换次数比较少
 */
@SortMethod("选择排序")
public class SelectSorting implements ISorting {
    @Override
    public void sort(int[] array) {
        int min =0;
        int minIndex=0;
        int temp=0;
        for(int i=0;i<array.length-1;i++){
            min=array[i];
            for(int j=i;j<array.length;j++){
                if(array[j]<min){
                    min=array[j];
                    minIndex=j;
                }
            }
            temp=array[i];
            array[i]=min;
            array[minIndex]=temp;
        }
        System.out.println(Arrays.toString(array));
    }
}
