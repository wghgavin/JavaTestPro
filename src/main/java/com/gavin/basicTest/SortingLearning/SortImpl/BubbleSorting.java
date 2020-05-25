package com.gavin.basicTest.SortingLearning.SortImpl;

import com.gavin.basicTest.SortingLearning.Annotation.SortMethod;
import com.gavin.basicTest.SortingLearning.ISorting;

import java.util.Arrays;

/**
 * 冒泡排序(时间复杂度为O(n2))【稳定型】//从小到大
 * 优化，增加flag，标志没有进行对换，说明早已经排序成功
 */
@SortMethod("冒泡排序")
public class BubbleSorting implements ISorting {
    @Override
    public void sort(int[] array){
        int temp =0;
        boolean flag =false;
        //执行array长度减1次
        for (int i = 0; i < array.length - 1; i++) {
            flag=false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    //进行了交换
                    flag =true;
                    temp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            if(!flag){//没有进行交换
                break;//终止循环
            }
        }
        //Arrays.stream(array).forEach(System.out::println);
        //或者
        System.out.println(Arrays.toString(array));
    }
}
