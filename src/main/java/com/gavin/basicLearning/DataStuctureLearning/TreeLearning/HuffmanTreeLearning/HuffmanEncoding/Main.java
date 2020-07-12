package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.HuffmanTreeLearning.HuffmanEncoding;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Zip zip = new Zip();
//        byte[] zipBytes = zip.zipString("i like java java java ,do you like it");
//        Unzip unzip = new Unzip(zip.getHuffmanMaps());
//        byte[] unZipBytes = unzip.decode(zipBytes);
//        System.out.println(Arrays.toString(unZipBytes));
//        System.out.println(new String(unZipBytes));

        //测试压缩文件
        String src = "1.jpg";
        String des ="F:\\test.zip";
//        Zip zip = new Zip();
//        zip.zipFile(src,des);
        Unzip unzip = new Unzip();
        unzip.unZipFile(des,"F:\\1.jpg");
    }
}
