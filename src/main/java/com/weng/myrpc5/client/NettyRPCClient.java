package com.weng.myrpc5.client;

import com.weng.myrpc5.common.RPCRequest;
import com.weng.myrpc5.common.RPCResponse;
import com.weng.myrpc5.register.ServiceRegister;
import com.weng.myrpc5.register.ZkServiceRegister;
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
 * @Date: 2022/2/10 14:58
 */

public class NettyRPCClient implements RPCClient {
    //Bootstrap 是 Netty 提供的一个便利的工厂类，可以通过它来完成 Netty 的客户端或服务器端的 Netty 初始化。
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private String host;
    private int port;
    private ServiceRegister serviceRegister;

    //zookeeper的加入
    public NettyRPCClient() {
        this.serviceRegister = new ZkServiceRegister();
    }

//    public NettyRPCClient() {
//        this.host = host;
//        this.port = port;
//    }

    static {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    //这里需要操作一下，因为netty的传输都是异步的，你发送request，会立刻返回一个值， 而不是想要的相应的response
    @Override
    public RPCResponse sentRPCRequest(RPCRequest rpcRequest) {

        try {
            //sync():数据同步写入磁盘
            InetSocketAddress address = serviceRegister.serviceDiscovery(rpcRequest.getInterfaceName());
            host = address.getHostName();
            port = address.getPort();
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            // 发送数据
            channel.writeAndFlush(rpcRequest);
            channel.closeFuture().sync();
            // 阻塞的获得结果，通过给channel设计别名，获取特定名字下的channel中的内容（这个在hanlder中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 实际上不应通过阻塞，可通过回调函数，后面可以再进行优化
            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
            RPCResponse rpcResponse = channel.attr(key).get();
            System.out.println("myrpc5的响应数据---->"+rpcResponse);
            return rpcResponse;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
