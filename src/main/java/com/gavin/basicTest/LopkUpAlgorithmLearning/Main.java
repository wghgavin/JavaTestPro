package com.gavin.basicTest.LopkUpAlgorithmLearning;

import com.gavin.basicTest.SortingLearning.ISorting;

import java.util.Random;
import java.util.Scanner;
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {

    }
    public int lookup(int des){
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(8000000);
        }
        while (true) {
            System.out.println("请输入查找算法方法编号:1.二分查找");
            String input = sc.nextLine();
            if (!input.matches("[1-3]{1}")) {
                System.out.println("输入错误，请重新输入");
                continue;
            }
            int num = Integer.parseInt(input);
            ILookupMethod lookupMethod = null;
        }
    }
}
