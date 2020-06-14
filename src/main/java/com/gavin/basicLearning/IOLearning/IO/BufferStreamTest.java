package com.gavin.basicLearning.IOLearning.IO;

import java.io.*;

public class BufferStreamTest {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            BufferedInputStream input = new BufferedInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\java学习.txt"));
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("F:\\学习资料\\1.txt"));
//          int len =0;
//          while((len=input.read())!=-1){
//              output.write(len);
//          }
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.close();
            input.close();
            long end = System.currentTimeMillis();
            System.out.println("缓冲字节流" + (end - start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
