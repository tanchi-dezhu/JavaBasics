package com.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public static void main(String[] args) throws IOException {
        //建立udp的服务
        DatagramSocket datagramSocket = new DatagramSocket();
        //准备数据，把数据封装到数据包中。
        String data = "这个是我第一个udp的例子..";
        byte[] buf=new byte[1024];
        //创建了一个数据包
        DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length,InetAddress.getLocalHost() , 9090);
        //调用udp的服务发送数据包
        datagramSocket.send(packet);
        //关闭资源 ---实际上就是释放占用的端口号
//        datagramSocket.receive(packet);
//        DatagramPacket packet1 = new DatagramPacket(buf,buf.length);
//        System.out.println(new String(buf,0,packet1.getLength()));
        datagramSocket.close();

    }
}
