package com.weng.myrpc3.client;

import com.weng.myrpc3.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.util.jar.Attributes;

/**
 * @Author Hua
 * @Date: 2022/1/27 16:06
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RPCResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext chc, RPCResponse msg) throws Exception {
        // 接收到response, 给channel设计别名，让sendRequest里读取response
        AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
        chc.channel().attr(key).set(msg);
        chc.channel().close();

    }
}
