package com.weng.myrpc4.client;

import com.weng.myrpc4.common.RPCRequest;
import com.weng.myrpc4.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * @Author Hua
 * @Date: 2022/2/8 15:13
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RPCResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCResponse msg) throws Exception {
        // 接收到response, 给channel设计别名，让sendRequest里读取response
        AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
        channelHandlerContext.channel().attr(key).set(msg);
        channelHandlerContext.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
