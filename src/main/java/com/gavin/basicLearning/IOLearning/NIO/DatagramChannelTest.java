package com.gavin.basicLearning.IOLearning.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 基于udp的通道DatagramChannel
 */
public class DatagramChannelTest {
    public static void main(String[] args) {
        new Thread(() -> receive()).start();
        new Thread(() -> send()).start();
    }

    private static void send() {
        try {
            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(false);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            Scanner scan = new Scanner(System.in);
            while (scan.hasNext()) {
                String str = scan.nextLine();
                buf.put(new Date().toString().getBytes());
                buf.flip();
                dc.send(buf, new InetSocketAddress("127.0.0.100", 9898));
                buf.clear();
            }
            dc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receive() {
        try {
            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(false);
            dc.bind(new InetSocketAddress(9898));
            Selector selector = Selector.open();
            dc.register(selector, SelectionKey.OP_READ);
            while (selector.select() > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey sk = it.next();
                    if (sk.isReadable()) {
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        dc.receive(buf);
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, buf.limit()));
                    }

                }
               it.remove();//会把select()数据清掉,必须使用

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("运行结束");
    }
}
