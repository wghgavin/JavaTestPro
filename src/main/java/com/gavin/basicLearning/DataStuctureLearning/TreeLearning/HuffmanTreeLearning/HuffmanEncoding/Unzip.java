package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.HuffmanTreeLearning.HuffmanEncoding;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 思路：
 * 1.将huffmanCodeBytes重新转为二进制
 * 2.根据哈夫曼编码获取对应值
 */
public class Unzip {

    public void unZipFile(String src, String desFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            ois = new ObjectInputStream(is);
            //先读哈夫曼字节数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanMaps = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanMaps, huffmanBytes);
            os = new FileOutputStream(desFile);
            os.write(bytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * 完成对压缩数据的解码
     *
     * @return
     */
    public byte[] decode(Map<Byte, String> huffmanMap, byte[] huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < huffmanCodes.length; i++) {
            boolean flag = (i != huffmanCodes.length - 1);
            sb.append(byteToBitString(flag, huffmanCodes[i]));
        }
        //System.out.println(sb);
        //字符串按照指定赫夫曼编码进行解码
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanMap.entrySet()
        ) {
            map.put(entry.getValue(), entry.getKey());
        }
        LinkedList<Byte> list = new LinkedList();
//        if(sb.length()<50000){
        int slow = 0;
        int fast = 1;
        while (fast <= sb.length()) {
            String str = sb.substring(slow, fast);
            Byte b = map.get(str);
            if (b != null) {
                slow = fast;
                list.add(b);
            }
            fast++;
        }
//        }
//        else {
//            Executor executor = Executors.newFixedThreadPool(10);
//            int bound = (sb.length()+9)/10;
//            for(int i=0;i<10;i++){
//                int finalI = i;
//                executor.execute(()->{
//                   int slow = finalI *bound;
//                   int slow
//               });
//            }
//        }

        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * flag作用是最后一位不用补齐
     *
     * @param flag
     * @param b
     * @return
     */
    private String byteToBitString(boolean flag, byte b) {
        int temp = b;
        //如果是整数还存在补高位
        if (flag) {
            temp |= 256;//256:100000000
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (!flag) {//防止发生溢出
            return str;
        }
        return str.substring(str.length() - 8);
    }
}
