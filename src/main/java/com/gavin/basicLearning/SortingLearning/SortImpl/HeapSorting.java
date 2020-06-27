package com.gavin.basicLearning.SortingLearning.SortImpl;

import com.gavin.basicLearning.SortingLearning.Annotation.SortMethod;
import com.gavin.basicLearning.SortingLearning.ISorting;

import java.util.Arrays;

/**
 * 时间复杂度(O(nlogn)),不稳定，会打乱顺序
 */
@SortMethod(name = "堆排序",id=9)
public class HeapSorting implements ISorting {
    /**
     *       1
     *   2      3
     * 4   5  6   7
     * @param array
     */
    @Override
    public void sort(int[] array) {
        int temp =0;
        //先调整为大顶堆
        for(int i= array.length/2-1;i>=0;i--){//最后一个非叶子节点开始(画树状图可知原理)
           adjustHeap(array,i,array.length);
        }
        //将堆顶元素和末尾元素交换，将最大元素沉到数组末端,
        //重新调整结构，使其满足堆定义，然后继续交换对顶元素和当前末尾元素，反复执行调整和交换步骤，直到整个数组有序
        for(int j=array.length-1;j>0;j--){
              temp =array[j];
              array[j]=array[0];
              array[0]=temp;
              //再调整为大顶堆
              adjustHeap(array,0,j);
        }
        //System.out.println(Arrays.toString(array));
    }

    /**
     * 将堆调整为大顶堆
     * 举例 int[] arr ={4,6,8,5,9};=>i=1=>adjustHeap=>得到{4,9,8,5,6}
     * 如果再次调用adjustHeap传入的是i=0=>得到{4,9,8,5,6}=》{9,6,8,5,4}
     * @param arr
     * @param i 表示非叶子结点在数组中的索引
     * @param length 表示对多少个元素继续调整
     */
    private void adjustHeap(int arr[],int i,int length){
        int temp = arr[i];//先取出当前的元素值
        for(int k=i*2+1;k<length;k=k*2+1){//获取子节点
             if(k+1<length&&arr[k]<arr[k+1]){//说明左子节点小于右子节点
                 k++;//k指向右子节点
             }
             if(arr[k]>temp){//如果子节点大于父节点
                 arr[i]=arr[k];//把较大值赋给当前节点
                 i=k;//指向k，继续循环比较
             }
             else {
                 break;//因为是从左至右，从下至上
             }
        }
        //结束
        arr[i] = temp;
    }
}
