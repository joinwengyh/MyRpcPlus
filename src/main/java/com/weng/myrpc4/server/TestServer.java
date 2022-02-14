package com.weng.myrpc4.server;

import com.weng.myrpc4.service.BlogServiceImpl;
import com.weng.myrpc4.service.UserServiceImpl;

/**
 * @Author Hua
 * @Date: 2022/2/7 21:28
 */
public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.providerServiceInterface(userService);
        serviceProvider.providerServiceInterface(blogService);

          NettyRPCServer nettyRPCServer = new NettyRPCServer(serviceProvider);
                nettyRPCServer.start(4444);

        //-----------------------------------------------------------------------------------


//        SimpleRPCRPCServer simpleRPCRPCServer = new SimpleRPCRPCServer(serviceProvider);
//        simpleRPCRPCServer.start(4444);

//        ThreadPoolRPCRPCServer threadPoolRPCRPCServer = new ThreadPoolRPCRPCServer(serviceProvider);
//        threadPoolRPCRPCServer.start(4444);
    }
}
