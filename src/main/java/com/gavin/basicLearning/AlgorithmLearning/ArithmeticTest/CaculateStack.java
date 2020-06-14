package com.gavin.basicLearning.AlgorithmLearning.ArithmeticTest;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 加减乘除(不带括号)
 */
public class CaculateStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String data =  sc.nextLine();
            boolean b = Pattern.matches("[0-9\\+\\-\\*\\/]+",data);
            if(b){
                int result = new CaculateStack().calculate(data);
                System.out.println(result);
            }
        }
    }
    boolean isNum(char ch){
        return (ch>='0'&&ch<='9');
    }
    public int calculate(String data){
        Stack<Integer> numsStack = new Stack<>();
        Stack<Character> charStack = new Stack<>();
        int helpInt =0;
        for (int i=0;i<data.length();i++){
            char ch = data.charAt(i);
            if(isNum(ch)){
                //数字
                Integer num = ch-'0';
                if(i+1!=data.length()&&isNum(data.charAt(i+1))){
                    helpInt+=num*10;
                }
                else {
                    helpInt+=num;
                    numsStack.push(helpInt);
                    helpInt=0;
                }
            }
            else {
                if(charStack.empty()){
                    charStack.push(ch);
                    continue;
                }
                if(isLessOrEqualStackValue(charStack.peek(),ch)){
                    //如果当前比较小
                    //把数字拿出来
                    Integer n2 =numsStack.pop();
                    Integer n1 = numsStack.pop();
                    int result = caculateTwoNum(n1,n2,charStack.pop());
                    numsStack.push(result);
                }
                charStack.push(ch);
            }
        }
        while (numsStack.size()!=1){
            int n2= numsStack.pop();
            int n1 = numsStack.pop();
            char ch = charStack.pop();
            int result = caculateTwoNum(n1,n2,ch);
            numsStack.push(result);
        }
        return numsStack.pop();
    }
    public int caculateTwoNum(int n1,int n2,char ch){
        switch (ch){
            case '+':
                return n1+n2;
            case '-':
                return n1-n2;
            case '*':
                return n1*n2;
            case '/':
                return n1/n2;
        }
        return  0;
    }
    public boolean isLessOrEqualStackValue(char stackValue,char currentValue){
        if((stackValue=='*'||stackValue=='/')&&(currentValue=='-'||currentValue=='+')){
            return true;
        }
        return false;
    }
}
