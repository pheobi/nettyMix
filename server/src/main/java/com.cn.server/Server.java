package com.cn.server;

import com.cn.core.codc.RequestDecoder;
import com.cn.core.codc.ResponseEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liyahui
 * @create 2019-06-09
 */
public class Server {
    public static void main(String[] args) {
        // 服务类
        ServerBootstrap b = new ServerBootstrap();

        //创建boss和worker
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //设置循环线程组事例
            b.group(boosGroup,workerGroup);
            //设置channel工厂
            b.channel(NioServerSocketChannel.class);
            //设置通道
            b.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new RequestDecoder());
                    ch.pipeline().addLast(new ResponseEncoder());
                    ch.pipeline().addLast(new ServerHandler());
                }
            });
            b.option(ChannelOption.SO_BACKLOG,2048);//链接缓冲池队列大小
            //绑定端口
            b.bind(10102).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start");
    }
}
