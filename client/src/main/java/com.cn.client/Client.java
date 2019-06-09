package com.cn.client;


import com.cn.core.codc.RequestEncoder;
import com.cn.core.codc.ResponseDecoder;
import com.cn.core.model.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.concurrent.Worker;

import java.net.InetSocketAddress;
import java.security.PrivateKey;
import java.util.Scanner;

/**
 * @author liyahui
 * @create 2019-06-09
 */
public class Client {

    public static void main(String[] args){
        /**服务类*/
        Bootstrap bootstrap = new Bootstrap();
        /**线程池*/
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        /**会话*/
        Channel channel;
        //设置循环线程组事例
        bootstrap.group(workerGroup);
        //设置channel工厂
        bootstrap.channel(NioSocketChannel.class);
        //设置管道
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ResponseDecoder());
                ch.pipeline().addLast(new RequestEncoder());
                ch.pipeline().addLast(new ClientHandler());
            }
        });

        //连接服务器
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 10102));
        try {
            connect.sync();
            channel =connect.channel();
            while (true){
                System.out.println("请输入:");
                Scanner scanner = new Scanner(System.in);
                byte[] msg = scanner.next().getBytes();
                //connect.channel().writeAndFlush(msg);
                Request request = Request.valueOf((short)1, (short)1, msg);
                connect.channel().writeAndFlush(request);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    /**连接*/
    /*public void connect() throws IntereuptedException{
        //连接服务端
        bootstr
    }*/
}
