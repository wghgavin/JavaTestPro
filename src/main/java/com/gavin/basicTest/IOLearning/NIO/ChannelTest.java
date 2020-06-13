package com.gavin.basicTest.IOLearning.NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 一,通道(Channel):用于缓冲区数据的传输，本身不存储数据，需要配个缓冲区
 * -------------------------------------------------------------
 * 二.通道的主要实现类
 * java.nio,channels.channel接口
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * -------------------------------------------------------------
 * 三.获取通道
 * 1.java针对支持通道的类提供了getChannel()方法
 * 本地IO:
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * 网络IO:
 * Socket
 * ServerSocket
 * DatagramSocket
 * 2.在JDK1.7中NIO.2针对各个通道提供了静态方法open()
 * 3.jdk1.7中NIO.2的Files工具类的newByteChannel()
 * -------------------------------------------------------------------
 * 四:通道之间的数据传输
 * 1.transferForm()
 * 2.transferTo()
 * --------------------------------------------------------------------
 * 五.分散(Scatter)与聚集(Gather)
 * 1.分散读取(Scattering Reads):将通道中的数据分散到多个缓冲区中
 * 2.聚集写入(Gathering Writes):将多个缓冲区数据聚集到一个通道
 * ---------------------------------------------------------------------
 * 六.字符集:Charset
 * 编码:字符串->字节数组
 * 解码:字节数组->字符串
 */
public class ChannelTest {

    public static void main(String[] args) {
        new ChannelTest().CharsetTest2();
    }

    /**
     * 利用通道完成文件复制（非直接缓冲区）
     */
    public void channelTest1() {
        long start =System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //获取通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("log.txt");
            fos = new FileOutputStream("logCopy.txt");
            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //分配指定大小缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //将通道中数据存入缓冲区
            while (inChannel.read(buf) != -1) {
                buf.flip();//切换为读取数据模式
                outChannel.write(buf);
                buf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeChannel(inChannel);
            closeChannel(outChannel);
            closeInPutStream(fis);
            closeOutPutStream(fos);
        }
        long end =System.currentTimeMillis();
        System.out.println((end-start)+"ms");
    }
    /**
     * 利用内存映射(只有ByteBuffer支持)完成文件复制(直接缓冲区)
     * 与Test1区别：Test1要通过通道去读，通道去写,这种缓冲区不用，因为数据直接放到了物理内存,所以直接放数据到缓冲区就行了
     */
    public void channelTest2() {
        long start = System.currentTimeMillis();
        FileChannel inChannel=null;
        FileChannel outChannel=null;
        try {
            //这里使用jdk1.7的方式来创建channel
            inChannel = FileChannel.open(Paths.get("log.txt"), StandardOpenOption.READ);//读模式
            outChannel = FileChannel.open(Paths.get("logCopy.txt"), StandardOpenOption.WRITE,
                    StandardOpenOption.READ,StandardOpenOption.CREATE);//CREATE_NEW是只创建不覆盖，若存在会报错,CREATE是创建或覆盖
            //这里加读模式解释：不加会报错，因为下面MappedByteBuffer只有READ_WRITE模式
            //内存映射文件
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());
            //直接对缓冲区进行数据的读写操作
            byte[] bf = new byte[inMappedBuf.limit()];
            inMappedBuf.get(bf);
            outMappedBuf.put(bf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            closeChannel(inChannel);
            closeChannel(outChannel);

        }
        long end = System.currentTimeMillis();
        System.out.println((start-end)+"ms");
    }
    /**
     * 通道间的数据传输
     */
    public void channelTransferTest(){
      try{
          FileChannel inChannel = FileChannel.open(Paths.get("log.txt"),StandardOpenOption.READ);
          FileChannel outChannel =FileChannel.open(Paths.get("logCopy.txt"),StandardOpenOption.READ,
                  StandardOpenOption.WRITE,StandardOpenOption.CREATE);
          inChannel.transferTo(0,inChannel.size(),outChannel);
          //outChannel.transferFrom(inChannel,0,inChannel.size());与上面一样效果
          inChannel.close();
          outChannel.close();
      }
      catch (Exception e){
          e.printStackTrace();
      }
    }

    /**
     * 分散和聚集测试
     */
    public void gatherScatterTest(){
        try {
            RandomAccessFile rafIn = new RandomAccessFile("log.txt","rw");
            //获取通道
            FileChannel channel = rafIn.getChannel();
            //分配指定大小缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(100);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);

            //分散读取
            ByteBuffer[] bufs = {buf1,buf2};
            channel.read(bufs);
            for (ByteBuffer b:bufs
                 ) {
                b.flip();
            }
            System.out.print(new String(bufs[0].array(),0,bufs[0].limit()));
            System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));

            //聚集写入
            RandomAccessFile rafOut = new RandomAccessFile("logCopy.txt","rw");
            FileChannel channelOut = rafOut.getChannel();
            channelOut.write(bufs);
            rafOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试Charset,查看支持的字符集
     */
    public void CharsetTest(){
       SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String,Charset>> set = map.entrySet();
        for (Map.Entry<String,Charset> entry:set
             ) {
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

    /**
     * 编码解码测试
     */
    public void CharsetTest2(){
        Charset cs1 = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cbuf = CharBuffer.allocate(1024);
        cbuf.put("华为牛逼");
        cbuf.flip();
        try {
            //编码
            ByteBuffer bBuf = ce.encode(cbuf);
            for(int i=0;i<8;i++){
                System.out.println(bBuf.get());
            }
            //解码
            bBuf.flip();//更改为读取模式
            CharBuffer cbufDecode =  cd.decode(bBuf);
            System.out.println(cbufDecode.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void closeChannel(FileChannel channel) {
        if (channel != null) {
            try {
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void closeOutPutStream(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void closeInPutStream(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
