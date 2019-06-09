package com.cn.client;

import com.cn.core.model.Response;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author liyahui
 * @create 2019-06-09
 */
public class ClientHandler extends SimpleChannelInboundHandler<Response> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {
        handlerResponse(response);
    }

    /**消息处理*/
    private void handlerResponse(Response response) {
        System.out.println("收到消息 module:"+response.getModule()+" cmd:"+response.getCmd()+" state:"+response.getStateCode());
    }

    /**断开连接*/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与服务器断开连接");
    }
}
