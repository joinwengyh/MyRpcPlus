package com.weng.myrpc5.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/10 15:08
 */
@AllArgsConstructor
public class NettyRPCServer implements RPCServer{
    private ServiceProvider serviceProvider;
    @Override
    public void start(int port) {
        // netty 服务线程组boss负责建立连接， work负责具体的请求
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work =  new NioEventLoopGroup();
        System.out.println("Netty服务端启动了...");
        try {
        //Bootstrap 是 Netty 提供的一个便利的工厂类，可以通过它来完成 Netty 的客户端或服务器端的 Netty 初始化。
        // 启动netty服务器
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 初始化
        serverBootstrap.group(boss, work).channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInitializer(serviceProvider));
        // 同步阻塞
          ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("开始监听，端口为：" + channelFuture.channel().localAddress());
            // 死循环监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

    @Override
    public void stop() {

    }
}
