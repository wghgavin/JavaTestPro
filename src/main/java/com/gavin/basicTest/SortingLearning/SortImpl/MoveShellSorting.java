package com.gavin.basicTest.SortingLearning.SortImpl;

import com.gavin.basicTest.SortingLearning.Annotation.SortMethod;
import com.gavin.basicTest.SortingLearning.ISorting;

@SortMethod(name = "希尔排序(移动式)",id=5)
/**
 * 先找位置再插入，而不是无脑交换位置，极大地减少了时间消耗
 */
public class MoveShellSorting implements ISorting {
    @Override
    public void sort(int[] array) {

    }
}
