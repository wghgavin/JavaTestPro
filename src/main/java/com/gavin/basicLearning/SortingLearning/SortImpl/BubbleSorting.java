package com.gavin.basicLearning.SortingLearning.SortImpl;

import com.gavin.basicLearning.SortingLearning.Annotation.SortMethod;
import com.gavin.basicLearning.SortingLearning.ISorting;
/**
 * 冒泡排序【稳定型】//从小到大
 * 优化，增加flag，标志没有进行对换，说明早已经排序成功
 * 存在问题效率低
 * 【平均时间复杂度】O(n^2),最好情况O(n),最坏情况O(n^2)
 * 【空间复杂度】O(1)
 */
@SortMethod(name = "冒泡排序",id=1)
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
    }
}
