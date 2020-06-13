package com.gavin.basicTest.IOLearning.IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStreamTest {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            FileInputStream input = new FileInputStream("C:\\Users\\Administrator\\Desktop\\java学习.txt");
            FileOutputStream output = new FileOutputStream("F:\\学习资料\\2.txt");
//            int len =0;
//            while((len=input.read())!=-1){
//                output.write(len);
//            }
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.close();
            input.close();
            long end = System.currentTimeMillis();
            System.out.println("普通字节流" + (end - start) + "毫秒");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
