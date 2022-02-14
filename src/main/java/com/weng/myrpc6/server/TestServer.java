package com.weng.myrpc6.server;

import com.weng.myrpc6.service.BlogService;
import com.weng.myrpc6.service.BlogServiceImpl;
import com.weng.myrpc6.service.UserService;
import com.weng.myrpc6.service.UserServiceImpl;

/**
 * @Author Hua
 * @Date: 2022/2/12 17:18
 */
public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

        // 这里重用了服务暴露类，顺便在注册中心注册，实际上应分开，每个类做各自独立的事
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 6666);
        serviceProvider.providerServiceInterface(userService);
        serviceProvider.providerServiceInterface(blogService);

//        ServiceProvider serviceProvider = new ServiceProvider();
//        serviceProvider.providerServiceInterface(userService);
//        serviceProvider.providerServiceInterface(blogService);

        //Netty版本服务端
        NettyRPCServer nettyRPCServer = new NettyRPCServer(serviceProvider);
        nettyRPCServer.start(6666);


//        简单版服务端
//        SimpleRPCRPCServer simpleRPCRPCServer = new SimpleRPCRPCServer(serviceProvider);
//        simpleRPCRPCServer.start(6666);

        //线程池版服务端
//        ThreadPoolRPCRPCServer threadPoolRPCRPCServer = new ThreadPoolRPCRPCServer(serviceProvider);
//        threadPoolRPCRPCServer.start(6666);

    }
}
