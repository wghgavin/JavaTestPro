package com.gavin.basicLearning.IOLearning.IO;

import org.ghost4j.util.StreamGobbler;

import java.io.File;
import java.io.FileOutputStream;
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
        //关联源
        File src = new File("log.txt");
        //关联目的
        File desc = new File("b.txt");
        //获取源的总大小
        long length = src.length();
        // 开两条线程,并分配下载任务
        new DownLoadThread(0, src, desc, length / 2).start();
        new DownLoadThread(length / 2 , src, desc, length - (length / 2)).start();
    }
    static class DownLoadThread extends Thread{
        private long start;
        private File src;
        private long total;
        private File desc;
        /**
         *
         * @param start
         *            开始下载的位置
         * @param src
         *            要下载的文件
         * @param desc
         *            要下载的目的地
         * @param total
         *            要下载的总量
         */
        public DownLoadThread(long start, File src, File desc, long total) {
            this.start = start;
            this.src = src;
            this.desc = desc;
            this.total = total;
        }
        @Override
        public void run(){
            try {
                // 创建输入流关联源,因为要指定位置读和写,所以我们需要用随机访问流
                RandomAccessFile src = new RandomAccessFile(this.src, "rw");
                RandomAccessFile desc = new RandomAccessFile(this.desc, "rw");
                // 源和目的都要从start开始
                src.seek(start);
                desc.seek(start);
                // 开始读写
                byte[] arr = new byte[1024];
                int len;
                long count = 0;
                while ((len = src.read(arr)) != -1) {
                    //分三种情况
                    if (len + count > total) {
                        //1.当读取的时候操作自己该线程的下载总量的时候,需要改变len
                        len = (int) (total - count);
                        desc.write(arr, 0, len);
                        //证明该线程下载任务已经完毕,结束读写操作
                        break;
                    } else if (len + count < total) {
                        //2.证明还没有到下载总量,直接将内容写入
                        desc.write(arr, 0, len);
                        //并且使计数器任务累加
                        count += arr.length;
                    } else {
                        //3.证明改好到下载总量
                        desc.write(arr, 0, len);
                        //结束读写
                        break;
                    }
                }
                src.close();
                desc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
   }
}
