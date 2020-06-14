package com.gavin.basicLearning.AlgorithmLearning.ArithmeticTest;

import java.util.Stack;

/**
 * 中缀表达式转后缀表达式+后缀表达式求值（可带括号）
 */
public class SuffixConvert {
    public static void main(String[] args) {
        SuffixConvert suffixConvert = new SuffixConvert();
        StringBuilder sb =  suffixConvert.convert("1+((2+3)*4)-5");
        int result = suffixConvert.caculateResult(sb);
        System.out.println(result);
    }

    public StringBuilder convert(String data){
        Stack<Character> charStack = new Stack<>();
        StringBuilder suffixStr = new StringBuilder();
        //33*3*2+2
        for (int i=0;i<data.length();i++){
            //首先是判断是否是运算符
            char ch = data.charAt(i);
            if(isOperation(ch)){//运算符的情况
                if(charStack.empty()||charStack.peek()=='('||operationLevel(ch)>operationLevel(charStack.peek())){
                    //直接入栈的三种情况
                    charStack.push(ch);
                }
                else {
                    while (true){
                        if(charStack.empty()||charStack.peek()=='('||operationLevel(ch)>operationLevel(charStack.peek())){
                            break;
                        }//断的情况
                        char c= charStack.pop();
                        suffixStr.append(c+" ");
                    }
                    charStack.push(ch);
                }
            }
            else if(isNum(ch)){//数字情况
                suffixStr.append(ch);
                while (i+1!=data.length()&&isNum(data.charAt(i+1))){
                    i++;
                    suffixStr.append(data.charAt(i));
                }
                suffixStr.append(" ");
            }
            else if(ch=='('){//左括号直接入栈
                charStack.push(ch);
            }
            else if(ch==')'){//右括号的情况
                //把所有运算符拿出来知道遇到'('
                char c = charStack.pop();
                while (c!='('){
                    suffixStr.append(c+" ");
                    c=charStack.pop();
                }
            }
        }
        while (!charStack.empty()){
            suffixStr.append(charStack.pop()+" ");
        }

        return  suffixStr.deleteCharAt(suffixStr.length()-1);
    }

    public int caculateResult(StringBuilder sb){
        String[] strs = sb.toString().split(" ");
        Stack<Integer> intArray = new Stack<>();
        for (String str: strs
        ) {
            if(isNum(str)){//如果是数字,直接入栈
                intArray.push(Integer.parseInt(str));
            }
            else {
                int n2 = intArray.pop();
                int n1 = intArray.pop();
                int r =0;
                switch (str){
                    case "+":
                        r = n1+n2;
                        break;
                    case "-":
                        r=n1-n2;
                        break;
                    case "*":
                        r=n1*n2;
                        break;
                    case "/":
                        r=n1/n2;
                        break;
                }
                intArray.push(r);
            }
        }
        int result =-1;
        if(!intArray.empty()){
            result = intArray.pop();
        }
        return  result;
    }

    public boolean isNum(String str){
        return  str.matches("[0-9]+");
    }

    public boolean isOperation(char ch){
        return ("+-*/".indexOf(ch)!=-1);
    }
    public boolean isNum(char ch){
        if(ch>='0'&&ch<='9') return  true;
        return  false;
    }
    public int operationLevel(char ch){
        if("*/".indexOf(ch)!=-1){
            return 2;
        }
        return 1;
    }
}