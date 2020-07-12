package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.HuffmanTreeLearning.HuffmanEncoding;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Zip zip = new Zip();
        byte[] zipBytes = zip.zipString("i like java java java ,do you like it");
        Unzip unzip = new Unzip(zip.getHuffmanMaps());
        byte[] unZipBytes = unzip.decode(zipBytes);
        System.out.println(Arrays.toString(unZipBytes));
        System.out.println(new String(unZipBytes));
    }
}
