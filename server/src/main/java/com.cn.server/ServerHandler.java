package com.cn.server;

import com.cn.core.codc.RequestDecoder;
import com.cn.core.model.Request;
import com.cn.core.model.Response;
import com.cn.core.session.Session;
import com.cn.core.session.SessionImpl;
import com.sun.xml.internal.ws.resources.HandlerMessages;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import org.omg.CORBA.portable.InvokeHandler;

/**
 * @author liyahui
 * @create 2019-06-09
 */
public class ServerHandler extends SimpleChannelInboundHandler<Request> {
   /**接受消息*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        handlerMessage(new SessionImpl(ctx.channel()),request);
    }

    /**消息处理*/
    private void handlerMessage(SessionImpl session, Request request) {
        Response response = new Response(request);
        System.out.println("module:"+request.getModule()+"  "+"cmd: "+request.getCmd());

        //获取命令执行器
        //InvokeHandler

    }
}
