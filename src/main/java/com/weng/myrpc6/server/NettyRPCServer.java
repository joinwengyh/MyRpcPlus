package com.weng.myrpc6.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/13 15:38
 * 实现RPCServer接口，负责监听与发送数据
 */
@AllArgsConstructor
public class NettyRPCServer implements RPCServer {
    private ServiceProvider serviceProvider;

    @Override
    public void start(int port) {
        // netty 服务线程组boss负责建立连接， work负责具体的请求
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        System.out.println("Netty服务端启动了...");
        try {
            //server端引导类，来引导绑定和启动netty服务器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //装配ServerBootstrap
            serverBootstrap.group(boss, work)
                    //制定通道类型为NioServerSocketChannel，一种异步模式的可以监听新进来的TCP连接的通道
                    .channel(NioServerSocketChannel.class)
                    //设置childHandler执行所有的连接请求
                    .childHandler(new NettyServerInitializer(serviceProvider));

            //最后绑定服务器等待直到绑定完成，调用sync()方法会阻塞直到服务器完成绑定，然后服务器等待通道关闭
            //因为使用sync方法，所以关闭操作也会被阻塞。
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("开始监听，端口为：" + channelFuture.channel().localAddress());
            // 死循环监听
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
        @Override
        public void stop () {

        }
    }

