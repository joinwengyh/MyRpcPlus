package com.weng.myrpc3.server;

import com.weng.myrpc3.client.SimpleRPCClient;
import com.weng.myrpc3.service.BlogServiceImpl;
import com.weng.myrpc3.service.UserServiceImpl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Hua
 * @Date: 2022/1/27 11:39
 *
 *
 */
public class TestServer  {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

//        RPCServer rpcServer = new NettyRPCServer(serviceProvider);
//        rpcServer.start(8888);

        //------------------------------------------------------------------------------------------------

//        RPCServer rpcServer2 = new SimpleRPCRPCServer(serviceProvider);
//        rpcServer2.start(9999);


        //使用自定义构造函数的线程池
//        ThreadPoolRPCRPCServer threadPoolRPCRPCServer = new ThreadPoolRPCRPCServer(serviceProvider,
//                10, 999,
//                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(99));

        ThreadPoolRPCRPCServer threadPoolRPCRPCServer = new ThreadPoolRPCRPCServer(serviceProvider);
        threadPoolRPCRPCServer.start(9999);


    }
}
