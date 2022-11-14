package com.wzh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * webSocket编程
 */
public class ClientServer {

    public static void main(String[] args) {
        Socket client = new Socket();


        try {
            client.connect(new InetSocketAddress("www.baidu.com",80));
            // 等内核完成   三次握手 --> socket   queue   4元组
            // 建立连接

            // 请求百度主页 使用Http请求
            OutputStream out = client.getOutputStream();
            out.write("GET / HTTP/1.0\n\n".getBytes());
            // 发送给了内核的  send queue
            out.flush();

            InputStream in = client.getInputStream();
            // 对应到内核的 recv  queue

            // 程序里的字节数组  1MB
            byte[] bytes = new byte[1024*1024];
            int size = in.read(bytes);
            // 有几种可能
            // 1.recv queue 没有返回   阻塞队列
            // 2.读到了 直接返回
            System.out.println("size = " + size);

            if (size > 0){
                System.out.println(new String(bytes,0,size));
            }
            System.out.println("----------------------");
            size = in.read(bytes);
            System.out.println("size =  " + size);
            if (size > 0){
                System.out.println(new String(bytes,0,size));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
