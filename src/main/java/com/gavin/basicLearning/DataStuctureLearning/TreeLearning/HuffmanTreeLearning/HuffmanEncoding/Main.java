package com.gavin.basicLearning.DataStuctureLearning.TreeLearning.HuffmanTreeLearning.HuffmanEncoding;

import java.util.*;

/**
 *思路:
 * (1)CharNode{data{存放数据},count{权值},left和right}(data存储字符，count存储字符出现次数)
 * (2)得到字符串对应的byte数组
 * (3)编写一个方法，将准备构建赫夫曼树的Node节点放到list，
 * (4)通过list创建对应赫夫曼树
 */
public class Main {
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    public static void main(String[] args) {
        String content="i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        byte[] zipBytes =huffmanZip(bytes);
    }

    /**
     *
     * @param bytes 原始的字节数组
     * @return 经过哈夫曼编码压缩后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes){
        List<CharNode> nodes = getNodes(bytes);
        CharNode rootNode =  createHuffmanTree(nodes);
        //生成哈夫曼表
        getCodes(rootNode,"",new StringBuilder());
        //System.out.println(huffmanCodes);
        byte[] bs = zip(bytes,huffmanCodes);
        //System.out.println(bs.length);
        return bs;
    }
    /**
     * 压缩数组
     * @param bytes
     * @param huffmanCodes
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //转换完之后，再转成byte数组
        int strLength = stringBuilder.length();
        int byteLength = (strLength+7)/8;
        byte[] bs = new byte[byteLength];
        int index=0;
        for(int i=0;i<strLength;i+=8){
            String strByte;
            if(i+8>strLength){
                strByte=stringBuilder.substring(i);
            }
            else {
                strByte = stringBuilder.substring(i,i+8);
            }
            bs[index++]=(byte) Integer.parseInt(strByte,2);
        }
        return bs;
    }
    /**
     * 获得哈夫曼表
     * @param node 当前节点
     * @param code 当前的代码
     * @param stringBuilder
     */
    private static void getCodes(CharNode node,String code,StringBuilder stringBuilder){
        if(node!=null){
            StringBuilder sb = new StringBuilder(stringBuilder);
            sb.append(code);
            if(node.data==null){
                //说明是非叶子节点，//继续递归
                getCodes(node.left,"0",sb);
                getCodes(node.right,"1",sb);
            }
            else {
                //说明是叶子节点
                huffmanCodes.put(node.data,sb.toString());
            }
        }
    }
    /**
     * 拿到赫夫曼树
     * @param nodes
     * @return
     */
    private static CharNode createHuffmanTree(List<CharNode> nodes){
        CharNode parent = null;
        while (nodes.size()>1){
            Collections.sort(nodes);
            CharNode left = nodes.get(0);
            CharNode right = nodes.get(1);
            parent = new CharNode(null,left.count+right.count,left,right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return parent;
    }

    /**
     * 将数组转换为节点
     * @param bytes
     * @return
     */
    private static List<CharNode> getNodes(byte[] bytes){
        Map<Byte,Integer> maps = new HashMap<>();
        for (byte b:bytes
             ) {
            Integer count = maps.getOrDefault(b,0);
            if(count!=0){
                maps.put(b,count+1);
            }
            else {
                maps.put(b,1);
            }
        }
        List<CharNode> nodes = new LinkedList<>();
        for (Map.Entry<Byte,Integer> set:maps.entrySet()
             ) {
            CharNode node =new CharNode(set.getKey(),set.getValue());
            nodes.add(node);
        }
        return nodes;
    }
}
