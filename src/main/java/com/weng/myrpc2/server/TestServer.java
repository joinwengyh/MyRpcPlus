package com.weng.myrpc2.server;

import com.weng.myrpc2.service.BlogService;
import com.weng.myrpc2.service.BlogServiceImpl;
import com.weng.myrpc2.service.UserService;
import com.weng.myrpc2.service.UserServiceImpl;

import java.util.HashMap;

/**
 * @Author Hua
 * @Date: 2022/1/26 14:56
 */
public class TestServer {
    public static void main(String[] args) {
        // 这里先不去讨论实现其中的细节，因为这里还应该进行优化，我们先去把服务端代码松耦合，再回过来讨论
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

//        HashMap<String, Object> serviceProvide  = new HashMap<>();
//        // 暴露两个服务接口， 即在RPCServer中加一个HashMap
//        serviceProvide.put("userService", userService);
//        serviceProvide.put("blogService", blogService);

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer rpcServer = new ThreadPoolRPCRPCServer(serviceProvider);
        rpcServer.start(8889);
    }


}
