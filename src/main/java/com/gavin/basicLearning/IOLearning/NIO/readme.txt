简介:
java NIO于原来IO具有同样作用，但是使用方式不同，NIO支持面向缓冲区的，基于通道的IO操作，有更高效的读写操作
区别:
IO                       | NIO
面向流(Stream Oriented)   |面向缓冲区
阻塞IO(Blocking IO)       |非阻塞IO(Non Blocking IO)
(无)                     |选择器(Selector)(这是用于socket的):SelectableChannel的多路复用器，用于监控SelectableChannel的io状态
------------------------------------------------------
术语描述:
一：面向流:数据传输是直接通过数据流动(理解为水流)
特点:单向
从磁盘到程序:输入流
从程序到磁盘:输出流
二:面向缓冲区:间接通过缓冲区运输(缓冲区通过通道传输:理解为铁路，通过火车运输)
特点:双向
-------------------------------------------------
NIO核心：
NIO系统的核心在于：通道(channel)和缓冲区(buffer)，通道表示打开到IO设备(如:文件，套接字)
的连接。若需要使用NIO系统，需要获取用于连接IO设备的通道以及用于容纳数据的缓冲区，然后操作缓冲区，对数据处理
---------------------------------------------------
项目分层:
|-SocketIO:讲解阻塞式和非阻塞式的socketNio通信
##BlockingNIOTest:阻塞式NIO
##NonBlockingNIOTest:非阻塞式NIO
|-BuferrTest:缓冲区学习
|-ChannelTest:通道学习
|-DatagramChannelTest:基于UDP的通信通道学习
|-PipeTest:管道学习(线程间传输数据)



