package com.gavin.basicLearning.AlgorithmLearning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCodeQuestionLearning {
    public static void main(String[] args) {
        System.out.println(paragraphBackText("voabavo"));
    }

    /**
     * 段式回文
     * 举个例子，对于一般回文 “abcba” 是回文，
     * 而 “volvo” 不是，但如果我们把 “volvo” 分为 “vo”、"l"、"vo" 三段，则可以认为 “(vo)(l)(vo)” 是段式回文（分为 3 段）
     * 输入：text = "ghiabcdefhelloadamhelloabcdefghi"
     * 输出：7
     * 解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
     * 输入：text = "merchant"
     * 输出：1
     * 解释：我们可以把字符串拆分成 "(merchant)"。
     * 输入：text = "aaa"
     * 输出：3
     * 解释：我们可以把字符串拆分成 "(a)(a)(a)"。
     */
    private static int paragraphBackText(String str) {
        if(str.equals("")) return 0;
        int l = 0;
        int r = str.length()-1;
        while (l<r){
            String s1 =str.substring(0,l+1);
            String s2 =str.substring(r);
            if(s1.equals(s2)){
                return 2+ paragraphBackText(str.substring(l+1,r));
            }
            l++;
            r--;
        }
        return 1;
    }

    /**
     * 三数之和,一个包含n个整数的数组，判断数组是否存在三个元素，使得，a+b+c=0
     * 答案不可以包含重复的数组(数组内数字可以重复)
     * @param arr
     * @param dex
     */
    private static void sumOfThreeNum(int[] arr,int dex){

    }

}
