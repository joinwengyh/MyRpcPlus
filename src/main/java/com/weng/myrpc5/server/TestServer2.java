package com.weng.myrpc5.server;

import com.weng.myrpc5.service.BlogService;
import com.weng.myrpc5.service.BlogServiceImpl;
import com.weng.myrpc5.service.UserServiceImpl;

/**
 * @Author Hua
 * @Date: 2022/2/11 17:21
 */
public class TestServer2 {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 5556);
        serviceProvider.providerServiceInterface(userService);
        serviceProvider.providerServiceInterface(blogService);

        NettyRPCServer nettyRPCServer = new NettyRPCServer(serviceProvider);
        nettyRPCServer.start(5556);
    }
}
