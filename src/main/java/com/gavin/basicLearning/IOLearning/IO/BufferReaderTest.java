package com.gavin.basicLearning.IOLearning.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 读取txt文件
 */
public class BufferReaderTest {
    public static void main(String[] args) {
        File file = new File("log.txt");
        System.out.println(getFileContent(file));
    }
    static String getFileContent(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(s+System.lineSeparator());
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
