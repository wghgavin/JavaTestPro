package com.gavin.basicTest.SortingLearning;

import com.gavin.basicTest.SortingLearning.SortImpl.BubbleSorting;
import com.gavin.basicTest.SortingLearning.SortImpl.InsertSorting;
import com.gavin.basicTest.SortingLearning.SortImpl.SelectSorting;

import java.lang.reflect.Proxy;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        while (true) {
            System.out.println("请输入排序方法编号:1.冒泡排序,2.选择排序,3.插入排序");
            String input =sc.nextLine();
            if(!input.matches("[1-3]{1}")){
                System.out.println("输入错误，请重新输入");
                continue;
            }
            int num = Integer.parseInt(input);
            ISorting sortMethod = null;
            SortingProxyHandle handle;
            switch (num){
                case 1:
                    sortMethod = new BubbleSorting();
                    break;
                case 2:
                    sortMethod=new SelectSorting();
                    break;
                case 3:
                    sortMethod = new InsertSorting();
                    break;
            }
            if(sortMethod==null){
                System.out.println("输入错误，请重新输入");
                continue;
            }
            int[] array = new int[80000];
            for (int i=0;i<array.length;i++){
                array[i]=r.nextInt(800000);
            }
            ClassLoader classLoader = sortMethod.getClass().getClassLoader();
            Class<?>[] interfaces = sortMethod.getClass().getInterfaces();
            handle=new SortingProxyHandle(sortMethod);
            ISorting instance = (ISorting)Proxy.newProxyInstance(classLoader,interfaces,handle);
            instance.sort(array);
        }

    }
}
