package com.weng.myrpc6.server;

import com.weng.myrpc6.codec.JsonSerializer;
import com.weng.myrpc6.codec.MyDecode;
import com.weng.myrpc6.codec.MyEncode;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/13 16:19
 * 初始化，主要负责序列化的编码解码， 需要解决netty的粘包问题
 */
@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 使用自定义的编解码器
        // 编码需要传入序列化器，这里是json，还支持ObjectSerializer，也可以自己实现其他的
        pipeline.addLast(new MyEncode(new JsonSerializer()));
        pipeline.addLast(new MyDecode());
        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));
    }
}
