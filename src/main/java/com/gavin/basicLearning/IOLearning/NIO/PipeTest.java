package com.gavin.basicLearning.IOLearning.NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java NIO管道是两个线程之间的单向数据连接.Pipe有一个source通道和一个sink
 * 通道.数据会被写到sink通道，从source通道读取
 * 模型如下:
 * ThreadA->Pipe(SinkChannel->SourceChannel)->ThreadB
 */
public class PipeTest {
    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            executorService.execute(new Sender(pipe));
            executorService.execute(new Receiver(pipe));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Sender implements Runnable {
        Pipe pipe;

        public Sender(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            try {
                Pipe.SinkChannel sinkChannel = pipe.sink();
                ByteBuffer buf = ByteBuffer.allocate(1024);
                Scanner sc = new Scanner(System.in);
                while (sc.hasNext()) {
                    String input = sc.nextLine();
                    buf.put(input.getBytes());
                    buf.flip();
                    sinkChannel.write(buf);
                    buf.clear();
                    if (input.equals("exit")) break;
                }
                sinkChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Receiver implements Runnable {
        Pipe pipe;

        public Receiver(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            try {
                Pipe.SourceChannel sourceChannel = pipe.source();
                ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
                while (true) {
                    int len = sourceChannel.read(buf);
                    buf.flip();
                    String out = new String(buf.array(), 0, len);
                    if (out.equals("exit")) break;
                    System.out.println(out);
                }
                sourceChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
