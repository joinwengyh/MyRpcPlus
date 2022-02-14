package com.weng.myrpc5.client;

import com.weng.myrpc5.common.RPCRequest;
import com.weng.myrpc5.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * @Author Hua
 * @Date: 2022/2/10 15:28
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RPCResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCResponse rpcResponse) throws Exception {
        // 接收到response, 给channel设计别名，让sendRequest里读取response
        System.out.println("NettyClientHandler客户端接收到消息:"+rpcResponse);
        AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
        channelHandlerContext.channel().attr(key).set(rpcResponse);
        channelHandlerContext.channel().close();
    }
}
