package com.gavin.basicLearning.IOLearning.IO;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 1.RandomAccessfile的四种模式
 * RandomAccessFile提供的四种模式："r"(只读), "rw"(读写访问), "rws"(同步数据/元数据更新的读写), "rwd"(同步数据更新的读写).
 * 同步更新确保对文件的更改被安全地写入到底层的存储设备中。如果不使用同步模式，在系统崩溃是肯定会丢失数据。
 * "rws"选项确保内容和元数据(例如文件的最近更改时间戳等信息)的持久化,而"rwd"选项只确保内容的更新
 * ---------------------------------------------------------------------
 * 2.randomAccessFile可以访问文件的任意地方同时支持文件的读和写,并且支持随机访问
 * ----------------------------------------------------------------------
 * 3.RandomAccessFile是单独的类，集成于Object
 * ----------------------------------------------------------------------
 * 4.RandomAccessFile只能对文件操作
 * ----------------------------------------------------------------------
 */
public class RandomAccessfileTest {

    public static void main(String[] args) {
    }
    public static class DownLoadFileUtil {
        private File src;
        private File dst;
        private int threadNum;
        private int blockSize;

        public DownLoadFileUtil(File src, File dst, int threadNum) {
            this.src = src;
            this.dst = src;
            this.threadNum = threadNum;
            this.blockSize = (int)(src.length() % threadNum == 0 ? src.length() / threadNum : src.length() / threadNum + 1);
        }
        // 开启多线程下载
        public void download() {
            // 循环开多个线程
            for (int i = 0; i < threadNum; i++) {
                DownLoadThread thread = new DownLoadThread(i);
                thread.setName("线程" + (i + 1));
                thread.start();
            }
        }

        // 定义一个下载线程
        private class DownLoadThread extends Thread {
            //表示第几个线程
            private int index;
            //起始位置
            private long startPos;
            private int blockSize;

            public DownLoadThread(int index) {
                this.index = index;
                startPos = index * DownLoadFileUtil.this.blockSize;
                this.blockSize = DownLoadFileUtil.this.blockSize;
            }

            @Override
            public void run() {
                RandomAccessFile rafR = null;
                RandomAccessFile rafW = null;
                try {
                    // 要读入的文件
                    rafR = new RandomAccessFile(src, "r");
                    // 要写入的文件
                    rafW = new RandomAccessFile(dst, "rw");
                    rafR.seek(startPos);
                    rafW.seek(startPos);
                    //缓冲区大小
                    byte[] buf = new byte[1024*1024];
                    int len = -1;
                    while (blockSize>0){
                        len = Math.min(blockSize, buf.length);
                        rafR.read(buf,0, len);
                        rafW.write(buf, 0, len);
                        blockSize -= len;
                    }
                    System.out.println(Thread.currentThread().getName() + "下载完毕");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    try {
                        if (rafR != null) {
                            rafR.close();
                        }
                        if (rafW != null) {
                            rafW.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
