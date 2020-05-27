package com.gavin.basicTest.SortingLearning.SortImpl;

import com.gavin.basicTest.SortingLearning.Annotation.SortMethod;
import com.gavin.basicTest.SortingLearning.ISorting;

/**
 * 先找位置再插入，而不是无脑交换位置，极大地减少了时间消耗(不稳定型（会破坏结构）,
 * 时间复杂度<O(n^2))//比插入排序快，是优化了的插入排序
 */
@SortMethod(name = "希尔排序(移动式)",id=5)
public class MoveShellSorting implements ISorting {
    @Override
    public void sort(int[] array) {
        int temp=0;
        for(int gap=array.length/2;gap>0;gap/=2){//先分组
          for(int i=gap;i<array.length;i++){
              int j =i;
              temp = array[j];//记录需要的值
              while(j-gap>=0&&temp>array[j-gap]){//一直向左找
                  //找到就往右移动
                  array[j]=array[j-gap];
                  j-=gap;
              }
              //跳出了说明就是这个位置就是需要移动的地方
              array[j]=temp;
          }
        }
    }
}
