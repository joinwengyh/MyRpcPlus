package com.weng.myrpc4.server;


import com.weng.myrpc4.codec.JsonSerializer;
import com.weng.myrpc4.codec.MyDecode;
import com.weng.myrpc4.codec.MyEncode;
import com.weng.myrpc4.codec.ObjectSerializer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/8 11:30
 * 初始化，主要负责序列化的编码解码， 需要解决netty的粘包问题
 */
@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
//        ChannelPipeline pipeline = socketChannel.pipeline();
//
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        pipeline.addLast(new LengthFieldPrepender(4));
//        pipeline.addLast(new ObjectEncoder());
//        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
//            @Override
//            public Class<?> resolve(String s) throws ClassNotFoundException {
//                return Class.forName(s);
//            }
//        }));
//        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));


        //----------------------------------------------------------------------------

        // 使用自定义的编解码器
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 编码需要传入序列化器，这里是json，还支持ObjectSerializer，也可以自己实现其他的

        pipeline.addLast(new MyDecode());
        pipeline.addLast(new MyEncode(new JsonSerializer()));

        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));
    }
}


