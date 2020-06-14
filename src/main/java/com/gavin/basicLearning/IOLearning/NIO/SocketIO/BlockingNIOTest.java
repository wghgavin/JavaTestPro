package com.gavin.basicLearning.IOLearning.NIO.SocketIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 阻塞式的NIO
 */
public class BlockingNIOTest {
    public static void main(String[] args) {
       new Thread(()->{serverTest();}).start();
       new Thread(()->clientTest()).start();

    }

    /**
     * 客户端
     */
    private static void clientTest() {
        try {
            //获取通道
            SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
            FileChannel inChannel = FileChannel.open(Paths.get("E:\\照片\\4ad4f2aa2df8cf8f931c883c2926ace.jpg"), StandardOpenOption.READ);
            //分配缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //读取本地文件，并发送到服务器
            while (inChannel.read(buf) != -1) {
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }
            sChannel.shutdownOutput();//需要关闭，否则服务端会不停接收(若不理解可以尝试去掉再执行代码)
            while (sChannel.read(buf)!=-1){
                buf.flip();
                System.out.println(new String(buf.array(),0,buf.limit()));
                buf.clear();
            }
            //关闭通道
            inChannel.close();
            sChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *服务端
     */
    private static void serverTest() {
        try {
            //获取链接
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            //把从客户端取到的数据数据保存的channel
            FileChannel outChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.WRITE,
                    StandardOpenOption.READ, StandardOpenOption.CREATE);
            //绑定端口号
            serverChannel.bind(new InetSocketAddress(9898));
            //获取客户端的channel
            SocketChannel cleintChannel = serverChannel.accept();
            //分配指定大小的缓冲区
            ByteBuffer outBuf = ByteBuffer.allocate(1024);
            while (cleintChannel.read(outBuf) != -1) {
                outBuf.flip();
                outChannel.write(outBuf);
                outBuf.clear();
            }
            outBuf.put("服务端接收数据成功".getBytes());
            outBuf.flip();
            cleintChannel.write(outBuf);
            cleintChannel.close();
            outChannel.close();
            serverChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
