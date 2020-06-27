package com.gavin.basicLearning.IOLearning.NIO.GroupChatSystem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {
    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        Server server = new Server();
        server.listen();
    }
    public Server() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //监听本地8888端口
            serverSocketChannel.bind(new InetSocketAddress(8888));
            //设置非阻塞
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //监听
    public void listen() {
        try {
            while (true) {
                int count = selector.select(5000);
                if (count > 0) {//有客户端的连接
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        //监听accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = serverSocketChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + "上线");
                        } else if (key.isReadable()) {
                            readData(key);
                        }
                        it.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //读客户端传来的数据
    private void readData(SelectionKey key) {
        SocketChannel sc =null;
        try {
            sc = (SocketChannel) key.channel();
            ByteBuffer buf = ByteBuffer.allocate(1024*1024);
            if (sc.read(buf)!=-1){
                buf.flip();
                String msg = new String(buf.array(),0,buf.limit());
                System.out.println("from客户端"+sc.getRemoteAddress()+"消息:"+msg);
                //向其他客户端转发消息
                sendInfoToOtherClient(msg,sc);
            }
        } catch (Exception e) {
            try {
                System.out.println(sc.getRemoteAddress()+"离线了");
                //取消注册
                key.cancel();
                //关闭通道
                sc.close();
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        }
    }
    //转发
    private void sendInfoToOtherClient(String msg,SocketChannel self){
        try {
            System.out.println("服务器向客户端转发消息中...");
            //遍历 所有注册到selector上的SocketChannel,并排除self
            for(SelectionKey key:selector.keys()){
                //通过key 取出对应socketChannel
                Channel targetChannel = key.channel();
                //有可能是serverSocketChannel
                if(targetChannel instanceof SocketChannel && targetChannel!=self){
                    SocketChannel channel = (SocketChannel) targetChannel;
                    ByteBuffer buf = ByteBuffer.wrap(msg.getBytes());
                    channel.write(buf);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
