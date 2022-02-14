package com.weng.myrpc5.server;

import com.weng.myrpc5.codec.JsonSerializer;
import com.weng.myrpc5.codec.MyDecode;
import com.weng.myrpc5.codec.MyEncode;
import com.weng.myrpc5.codec.ObjectSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/10 15:14
 * 初始化，主要负责序列化的编码解码， 需要解决netty的粘包问题
 */
@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new MyEncode(new JsonSerializer()));
        pipeline.addLast(new MyDecode());
        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));


    }
}
