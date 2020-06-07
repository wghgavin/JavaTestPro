package com.gavin.basicTest.AlgorithmLearning.CursionLearning;

/**
 * 八皇后（回溯）
 */
public class BaHuangHou {
    int max=8;
    int[] array=new int[8];
    static int matchResult =0;
    public static void main(String[] args) {
        new BaHuangHou().func(0);
        System.out.println("一共有"+ matchResult+"种");
    }


    /**
     *
     * @param n 第n个皇后
     */
    public void func(int n){
        if(n==max){
            print();
            matchResult++;
            return;
        }else {
            for(int i=0;i<max;i++){//从0到max-1列
                array[n]=i;
                if(judge(n)){//如果对的，那就继续放
                    func(n+1);
                }
            }
        }
    }
    public void print(){
        for (int n:array
        ) {
            System.out.print(n+",");
        }
        System.out.println();
    }

    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    public boolean judge(int n){//看看前面的有没有放的冲突的,true为不冲突,false为冲突
        for(int i=0;i<n;i++){
            //对角线或者同一列
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i]))return false;
        }
        return  true;
    }
}
