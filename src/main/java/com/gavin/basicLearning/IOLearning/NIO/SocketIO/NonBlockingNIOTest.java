package com.gavin.basicLearning.IOLearning.NIO.SocketIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * 非阻塞式NIO
 * Selector选择的监听事件类型
 * 读SelectionKey.IO_READ(1)
 * 写:SelectionKey.OP_WRITE(4)
 * 连接:SelectionKey.OP_CONNECT
 * 接受:SelectionKey.OP_ACCEPT
 */
public class NonBlockingNIOTest {
    public static void main(String[] args) {
        new Thread(()->server()).start();
        new Thread(()->cleint()).start();
        new Thread(()->cleint()).start();
        new Thread(()->cleint()).start();
    }

    /**
     * 客户端
     */
    private static void cleint() {
        try {
            //获取通道
            SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
            //切换至非阻塞模式
            clientChannel.configureBlocking(false);
            //分配制定大小缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.put(new Date().toString().getBytes());
            buf.flip();
            clientChannel.write(buf);
            buf.clear();
            clientChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务端
     */
    private static void server() {
        try {
            //1.获取通道
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            //2.切换非阻塞模式
            serverChannel.configureBlocking(false);
            //3.绑定连接
            serverChannel.bind(new InetSocketAddress(9898));
            //4.获取选择器
            Selector selector = Selector.open();
            //5.将通道注册到选择器,制定监听接收事件
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
           // System.out.println(selector.select());
            //6.轮询式获取选择器上已经准备就绪的事件
            while (selector.select() > 0) {
                //7.获取当前选择器中所有注册的选择键
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    //8.获取准备就绪的事件
                    SelectionKey sk = it.next();
                    //9.判断具体是什么事件
                    if (sk.isAcceptable()) {
                        //10.获取客户端连接
                        SocketChannel cleintSocket = serverChannel.accept();
                        //11.切换非阻塞模式
                        cleintSocket.configureBlocking(false);
                        //12.将该通道注册到selector
                        cleintSocket.register(selector, SelectionKey.OP_READ);
                    } else if (sk.isReadable()) {
                        //13.获取当前先择期上"读就绪"状态的通道
                        SocketChannel clientChannel = (SocketChannel) sk.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        while (clientChannel.read(buf)>0){
                            buf.flip();
                            System.out.println(new String(buf.array(),0,buf.limit()));
                            buf.clear();
                        }
                    }
                    //取消选择键
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
