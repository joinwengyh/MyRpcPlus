package com.weng.myrpc6.client;

import com.weng.myrpc6.common.RPCRequest;
import com.weng.myrpc6.common.RPCResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * @Author Hua
 * @Date: 2022/2/13 16:35
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RPCResponse> {


    //从服务器收到数据后调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCResponse rpcResponse) throws Exception {
        // 接收到response, 给channel设计别名，让sendRequest里读取response
        AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
        ctx.channel().attr(key).set(rpcResponse);
        ctx.channel().closeFuture();
    }
}
