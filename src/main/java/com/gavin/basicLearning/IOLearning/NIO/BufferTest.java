package com.gavin.basicLearning.IOLearning.NIO;

import java.nio.ByteBuffer;

/**
 * 缓冲区(Buffer)：在java中负责数据的存储(底层就是数组)
 * 一.类型
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 管理方式一致：通过allocate()获取缓冲区
 * ---------------------------------------
 * 二:核心方法
 * 1.put();存入数据到缓冲区
 * 2.get():获取缓冲区数据
 * ----------------------------------------
 * 三:四个核心属性
 * 1.capacity:容量，最大数据容量，一旦声明不能改变
 * 2.limit:界限,表示缓冲区可以操作的数据大小，limit后数据不能读写
 * 3.position:位置，表示缓冲区正在操作数据的位置
 * 4.position<=limit<=capacity
 * 特殊方法:
 * mark():标记，表示记录当前position的位置，可以通过reset()回复到mark位置
 * -----------------------------------------
 * 四:直接缓冲区和非直接缓冲区
 * 1.非直接缓冲区:通过allocate(),方法分配缓冲区，将缓冲区简历在JVM的内存
 * 2.直接缓冲区:通过allocateDirect 方法分配直接缓冲区,将缓冲区建立在物理内存,直接字节缓冲区
 * 还可以通过FileChannel的map()方法将文件区域直接映射到内存中来创建，该方法返回MappedByteBuffer
 * (不易控制，耗损大)
 *
 * 相关方法isDirect():判断是否是直接缓冲区
 * ---------------------------------------------------------------
 */
public class BufferTest {
    public static void main(String[] args) {
         new BufferTest().ByteBufferTest();
    }
    public void ByteBufferTest(){
        //1.分配指定大小缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
        System.out.println(buf.position());
        System.out.println("---------------------------");
        //2传入一个数据
        String str1 ="abcde";
        buf.put(str1.getBytes());
        System.out.println(buf.position());//写模式下的position
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println("---------------------------");
        //3.切换成读取模式
        buf.flip();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println("---------------------------");
        //4.利用get读取缓冲区中的数据
        byte[] b2 =new byte[buf.limit()];
        buf.get(b2);
        System.out.println(new String(b2,0,b2.length));
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println("---------------------------");
        //5.rewind:可重复读数据，把position置为0
        buf.rewind();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println("---------------------------");
        //5.clear清楚缓冲区数据,但里面的数据依然存在，只是处于"被遗忘状态",因为position回到最初状态
        buf.clear();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        //使用mark记录当前position的位置，可以通过reset()回复到mark位置
//        buf.reset();
//        System.out.println(buf.position());
//        System.out.println(buf.limit());
//        System.out.println(buf.capacity());
    }
}
