package com.gavin.basicLearning.SortingLearning.SortImpl;

import com.gavin.basicLearning.SortingLearning.Annotation.SortMethod;
import com.gavin.basicLearning.SortingLearning.ISorting;

/**
 * 希尔排序(交换式)https://www.cnblogs.com/MWCloud/p/11244163.html
 * 把记录按下标的一定增量分组，对每组使用直接插入排序算法排序:随着增量逐渐减少，每组
 * 包含的关键词越来越多，当增量减至1，整个文件被分成一组，算法就终止
 * 【平均时间复杂度】O(n^2),最好情况O(n^2),最坏情况O(n^2)
 * 【空间复杂度】O(1)
 */
@SortMethod(name = "希尔排序(交换式)",id=4)
public class ChangeShellSorting implements ISorting {
    @Override
    public void sort(int[] array) {
        /**
         * 逐步交换:[8,9,1,7,2,3,5,4,6,0];
         * 第一次交换(10/2=5组)[3,5,1,6,0,8,9,4,7,2]
         * 第二次交换(5/2=2组)[0,2,1,4,3,5,7,6,9,8]
         * 第三次交换(2/2=1组)[0,1,2,3,4,5,6,7,8,9]
         */
        int temp=0;
        for(int gap= array.length/2;gap>0;gap/=2){
            //使用冒泡交换
            for(int i=gap;i<array.length;i++){
                for(int j=i-gap;j>=0;j-=gap){
                    if(array[j]>array[j+gap]){
                        temp=array[j];
                        array[j] = array[j+gap];
                        array[j+gap]=temp;
                    }
                }
            }
        }
    }
}
