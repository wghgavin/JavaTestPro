package com.gavin.basicLearning.IOLearning.NIO.GroupChatSystem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class Client {
    private final String HOST ="127.0.0.1";//服务器ip
    private final int PORT=8888;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public static void main(String[] args) {
        Client client = new Client();
        new Thread(()->{
            while (true){
                client.readInfo();
            }
        }).start();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String input = sc.nextLine();
            client.sendInfo(input);
        }
    }
    public Client(){
        try {
            selector=Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            username=socketChannel.getLocalAddress().toString();//本地地址
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //向服务器发送消息
    public void sendInfo(String info){
        try{
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
            System.out.println("向服务端发送信息"+info);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //从服务读取端回复的消息
    public void readInfo(){
        try{
            //有可以用的通道
            if (selector.select()>0){
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey key = it.next();
                    if(key.isReadable()){
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buf =ByteBuffer.allocate(1024*1024);
                        //读取
                        sc.read(buf);
                        buf.flip();
                        String msg = new String(buf.array(),0,buf.limit());
                        System.out.println("客户端"+username+"接收到"+msg);
                    }
                }
            }
        }
        catch (Exception e){
          e.printStackTrace();
        }
    }
}
