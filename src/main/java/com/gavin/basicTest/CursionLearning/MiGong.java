package com.gavin.basicTest.CursionLearning;

import java.util.Stack;

/**
 * 迷宫单路径(回溯)
 */
public class MiGong {

    public static void main(String[] args) {
        int[][] array = new int[10][10];//定义一个10乘10的迷宫
        //定义规则 1：墙 2:记录走过的路 0:可以走的
        //赋值
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == 0 || i == array.length - 1) {
                    array[i][j] = 1;
                } else if (j == 0 || j == array[i].length - 1) {
                    array[i][j] = 1;
                } else if (i == 2 && (j == 1 || j == 2)) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = 0;
                }
            }
        }
        MiGong miGong = new MiGong();
        miGong.printArray(array);

        Stack<int[][]> arrayStack = new Stack<>();
        miGong.getSingleResult(array, 1, 1);
        System.out.println();
        miGong.printArray(array);
    }
    public void printArray(int[][] array){
        for (int[] arr:array
        ) {
            for (int n:arr
            ) {
                System.out.print(n+" ");
            }
            System.out.println();
        }
    }
    //自定义规则先下->右->上->左
    public void getReuslt(int[][] array, int i, int j, Stack<int[][]> arrayStack){
        if(i==j&&i==8){
            array[i][j]=2;
            arrayStack.push(array);
        }else {
            if(array[i][j]==0){
                array[i][j]=2;
                for(int k=1;k<=4;k++){
                    int[][] tempArray =new int[array.length][array[0].length];
                    System.arraycopy(array,0,tempArray,0,array.length);
                    goByDirection(tempArray,i,j,arrayStack,i);
                    System.out.println("执行");
                }
            }
            else {
                return;
            }
        }
    }
    public boolean getSingleResult(int[][] array,int i,int j){
        if(array[8][8]==2){
            return true;
        }
        else {
            if(array[i][j]==0){//说明有路
                array[i][j]=2;
                //有路变为2
                if(getSingleResult(array,i+1,j)){//向下有路
                    return true;
                }
                else if(getSingleResult(array,i,j+1)){//向右有路
                    return true;
                }
                else if(getSingleResult(array,i-1,j)){//向上
                    return true;
                }
                else  if ((getSingleResult(array,i,j-1))){//向左
                    return true;
                }
                else {//走过说明是死路
                    array[i][j]=3;
                    return false;
                }
            }
            else {//无路
                return false;
            }
        }
    }
    private void goByDirection(int[][] array, int i, int j, Stack<int[][]> arrayStack, int type){
        switch (type){
            case 1://下
                getReuslt(array,i+1,j,arrayStack);
                break;
            case 2://右
                getReuslt(array,i,j+1,arrayStack);
                break;
            case 3://上
                getReuslt(array,i-1,j,arrayStack);
                break;
            case 4://左
                getReuslt(array,i,j-1,arrayStack);
                break;
        }
    }
}