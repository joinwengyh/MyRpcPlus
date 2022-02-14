package com.weng.myrpc5.client;

import com.weng.myrpc5.codec.JsonSerializer;
import com.weng.myrpc5.codec.MyDecode;
import com.weng.myrpc5.codec.MyEncode;
import com.weng.myrpc5.codec.ObjectSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author Hua
 * @Date: 2022/2/10 15:06
 * 同样的与服务端解码和编码格式
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new MyEncode(new JsonSerializer()));
        pipeline.addLast(new MyDecode());
        pipeline.addLast(new NettyClientHandler());
    }
}
