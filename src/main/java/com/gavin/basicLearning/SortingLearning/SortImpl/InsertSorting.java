package com.gavin.basicLearning.SortingLearning.SortImpl;

import com.gavin.basicLearning.SortingLearning.Annotation.SortMethod;
import com.gavin.basicLearning.SortingLearning.ISorting;

/**
 * 插入排序（O(n^2)）(从小到大)【稳定】
 * 存在问题如果待插入的数特别小，那么会造成效率低下(所以引入了希尔排序)
 * 思想:
 * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只
 * 包含一个元素，无序表中包含n-1个元素，排序过程中每次从无序表中取出
 * 第一个元素，把它的排序码依次与有序表元素的排序码进行比较，把它插入到有序表的适当位置，使之成为新的
 * 有序表
 * 进行n-1次插入：for循环
 * 把第一个元素做有序表，从第二元素开始遍历
 * 3,5,2,1,6
 * 第一次插入:3,5,2,1,6
 * 第二次插入:2,3,5,1,6
 * 第三次插入:,1,2,3,5,6
 * 【平均时间复杂度】O(n^2),最好情况O(n),最坏情况O(n^2)
 * 【空间复杂度】O(1)
 */
@SortMethod(name="插入排序",id=3)
public class InsertSorting implements ISorting {
    @Override
    public void sort(int[] array) {
        int insertValue =0;
        int insertIndex =0;
        for(int i=1;i<array.length;i++){
            insertValue=array[i];//待插入对象
            insertIndex =i-1;//待插入的索引
            while(insertIndex>=0&&array[insertIndex]>insertValue){//insertIndex>0表示最后找到的不能越出界限，
                // array[insertIndex]>insertValue表示找到大于待插入的
                array[insertIndex+1]=array[insertIndex];//把带插入的对象向前挪
                insertIndex--;//继续往下找
            }
            //到达出口则把待插入对象放到该位置
            //这里判断是否需要赋值加快速度,因为可能出现array[inertIndex]<insertValue的情况
            if(insertIndex+1!=i){
                array[insertIndex+1] = insertValue;
            }
        }
    }
}
