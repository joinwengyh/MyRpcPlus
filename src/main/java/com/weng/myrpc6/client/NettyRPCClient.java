package com.weng.myrpc6.client;

import com.weng.myrpc6.common.RPCRequest;
import com.weng.myrpc6.common.RPCResponse;
import com.weng.myrpc6.register.ServiceRegister;
import com.weng.myrpc6.register.ZkServiceRegister;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.AllArgsConstructor;

import java.net.InetSocketAddress;

/**
 * @Author Hua
 * @Date: 2022/2/13 15:21
 * 实现RPCClient接口
 */
public class NettyRPCClient implements RPCClient {
    private String host;
    private int port;
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private ServiceRegister serviceRegister;

    public NettyRPCClient() {
        this.serviceRegister = new ZkServiceRegister();
    }

    // netty客户端初始化，重复使用
    static {
        //客户端引导类
        bootstrap = new Bootstrap();
        //EventLoopGroup可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(eventLoopGroup).//多线程处理
                channel(NioSocketChannel.class)//制定通道类型为NioSocketChannel
                .handler(new NettyClientInitializer());//业务处理类
    }

    //这里需要操作一下，因为netty的传输都是异步的，你发送request，
    // 会立刻返回一个值， 而不是想要的相应的response
    @Override
    public RPCResponse sendRequest(RPCRequest rpcRequest) {
        InetSocketAddress inetSocketAddress = serviceRegister.serviceDiscovery(rpcRequest.getInterfaceName());
        host=  inetSocketAddress.getHostName();
        port = inetSocketAddress.getPort();
        try {
            //连接服务器
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            // 发送数据
            channel.writeAndFlush(rpcRequest);
            channel.closeFuture().sync();
            // 阻塞的获得结果，通过给channel设计别名，
            // 获取特定名字下的channel中的内容（这个在NettyClientHandler中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 实际上不应通过阻塞，可通过回调函数，后面可以再进行优化
            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
            RPCResponse rpcResponse  = channel.attr(key).get();
            System.out.println(rpcResponse);
            return rpcResponse;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }


    }
}
