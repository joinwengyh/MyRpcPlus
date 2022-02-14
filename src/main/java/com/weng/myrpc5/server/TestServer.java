package com.weng.myrpc5.server;

import com.weng.myrpc5.service.BlogServiceImpl;
import com.weng.myrpc5.service.UserServiceImpl;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:55
 */
public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

        // 这里重用了服务暴露类，顺便在注册中心注册，实际上应分开，每个类做各自独立的事
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1",5555);
        serviceProvider.providerServiceInterface(userService);
        serviceProvider.providerServiceInterface(blogService);


        NettyRPCServer nettyRPCServer = new NettyRPCServer(serviceProvider);
       nettyRPCServer.start(5555);
//
//        SimpleRPCRPCServer simpleRPCRPCServer = new SimpleRPCRPCServer(serviceProvider);
//        simpleRPCRPCServer.start(5555);
    }
}
