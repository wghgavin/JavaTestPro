package com.gavin.basicLearning.AlgorithmLearning.SystemTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 十进制数转为n进制数测试
 * 输入 一个数为10进制数，空格隔开后一个数为转换的进制,
 * 遇到0 0则退出
 * 0.5 2
 * 输出
 * 0.1
 */
@SuppressWarnings("all")
public class DecConvertTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new LinkedList<>();
        while (sc.hasNext()) {
            list.clear();
            String input = sc.nextLine();
            if (input.equals("0 0")) break;
            String[] strs = input.split(" ");
            double value = Double.parseDouble(strs[0]);
            int n = Integer.parseInt(strs[1]);
            int sum = 0;
            double max = 0;
            int i = 0;//用来代表索引
            int j = 0;//用来代表每个次幂
            while (sum < value) {
                i++;
                max = 0;
                j=0;
                while (sum + (j + 1)/ Math.pow(n, i) <= value&&j+1<= n-1) {
                    j++;
                    max = j/ Math.pow(n, i);
                }
                //跳出了循环则说明大了,此时的max就是对应的值,j就是对应的次幂
                list.add(j);
                sum += max;
                if (i == 11) {
                    break;
                }
            }

            StringBuilder sb = new StringBuilder("0.");
            for (Integer num : list
            ) {
                sb.append(num);
            }
            int count = sb.length()-2;
            for (int a = 0; a < 10 -count; a++) {
                sb.append("0");
            }
            System.out.println(sb);
        }
    }
}
