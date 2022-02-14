package com.weng.myrpc6.server;

import com.weng.myrpc6.service.BlogServiceImpl;
import com.weng.myrpc6.service.UserServiceImpl;

/**
 * @Author Hua
 * @Date: 2022/2/14 11:06
 */
public class TestServer2 {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

        // 这里重用了服务暴露类，顺便在注册中心注册，实际上应分开，每个类做各自独立的事
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 7777);
        serviceProvider.providerServiceInterface(userService);
        serviceProvider.providerServiceInterface(blogService);

//        ServiceProvider serviceProvider = new ServiceProvider();
//        serviceProvider.providerServiceInterface(userService);
//        serviceProvider.providerServiceInterface(blogService);

        //Netty版本服务端
        NettyRPCServer nettyRPCServer = new NettyRPCServer(serviceProvider);
        nettyRPCServer.start(7777);
    }
}
