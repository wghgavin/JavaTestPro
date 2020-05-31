package com.gavin.basicTest.CursionLearning;

/**
 * 给出一个非负整数数组，你最初定位在数组的第一个位置。
 * 数组中的每个元素代表你在那个位置可以跳跃的最大长度。
 * 判断你是否能到达数组的最后一个位置。
 * A = [2,3,1,1,4]，返回 true.
 * A = [3,2,1,0,4]，返回 false.
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 0, 4};
        System.out.println(canJump(arr));
    }
    public static boolean canJump(int[] arr){
        boolean result =true;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(max<i){
                result =false;
                break;
            }
            if(i==arr.length-1)break;//增加速度的步骤
            max = Math.max(arr[i] + i, max);
        }
        return result;
    }
}
