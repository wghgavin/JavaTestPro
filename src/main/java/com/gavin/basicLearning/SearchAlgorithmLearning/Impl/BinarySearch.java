package com.gavin.basicLearning.SearchAlgorithmLearning.Impl;

import com.gavin.basicLearning.SearchAlgorithmLearning.Annotation.SearchMethod;
import com.gavin.basicLearning.SearchAlgorithmLearning.ISearchMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 【二分查找法】只适用于排好序的数组,从中间向两边查找
 */
@SearchMethod(name = "二分查找法",id=1)
public class BinarySearch implements ISearchMethod {
    @Override
    public List<Integer> lookup(int[] arr, int val) {
        return lookup(arr,0,arr.length-1,val);
    }
    private List<Integer> lookup(int[] arr, int left, int right, int val){
        if(left>right||val<arr[0]||val>arr[arr.length-1]) return null;
        int mid = (left+right)/2;
        if(arr[mid]==val) {
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
