package com.gavin.basicLearning.SearchAlgorithmLearning.Impl;

import com.gavin.basicLearning.SearchAlgorithmLearning.Annotation.SearchMethod;
import com.gavin.basicLearning.SearchAlgorithmLearning.ISearchMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 【插值查找法】由二分查找法演变过来二分查找是mid=left+(right-left)/2,适合分布比较均匀的有序数组
 * !!!!!!数据量大会发生栈溢出(因为如果数据量大并且数字分配不均匀，容易造成太多次递归)
 */
@SearchMethod(name = "插值查找法",id=2)
@SuppressWarnings("all")
public class InterpotationSearch implements ISearchMethod {

    @Override
    public List<Integer> lookup(int[] arr, int val) {
        return lookup(arr,0,arr.length-1,val);
    }
    private List<Integer> lookup(int[] arr, int left, int right, int val){
        if(left>right||val<arr[0]||val>arr[arr.length-1])return null;
        int mid =left+(val-arr[left])*(right-left)/(arr[right]-arr[left]);
        if(mid<0){
            System.out.println();
        }
        if(arr[mid]==val){
            List<Integer> list = new ArrayList<>();
            list.add(mid);
            int l = mid;
            int r = mid;
            while (l>0&&arr[--l]==val){
                list.add(l);
            }
            while (r<arr.length-1&&arr[++r]==val){
                list.add(r);
            }
            return list;
        }
        else if(arr[mid]<val) {
            return lookup(arr,mid+1,right,val);
        }
        else {
            return lookup(arr,left,mid-1,val);
        }
    }
}
