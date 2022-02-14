package com.weng.myrpc4.client;

import com.weng.myrpc4.common.Blog;
import com.weng.myrpc4.common.User;
import com.weng.myrpc4.service.BlogService;
import com.weng.myrpc4.service.UserService;

/**
 * @Author Hua
 * @Date: 2022/2/7 16:06
 */
public class TestClient {
    public static void main(String[] args) {
        // 构建一个使用java Socket/ netty/....传输的客户端
        NettyRPCClient nettyRPCClient = new NettyRPCClient("127.0.0.1", 4444);
        // 把这个客户端传入代理客户端
        RPCClientProxy rpcClientProxy = new RPCClientProxy(nettyRPCClient);
        // 代理客户端根据不同的服务，获得一个代理类， 并且这个代理类的方法以或者增强（封装数据，发送请求）
        UserService userService = rpcClientProxy.getProxy(UserService.class);

        // 调用方法
        User userByUserId = userService.getUserByUserId(4);
        System.out.println("从服务端得到的user为：" + userByUserId);

        Integer insert = userService.insert(new User().builder().userName("myrpc4").id(44).sex("nice").build());
        System.out.println("向服务端插入数据："+insert);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(444);
        System.out.println("从服务端得到的blog为：" + blogById);


        //----------------------------------------------------------------------------------------------

//        // 构建一个使用java Socket/ netty/....传输的客户端
//        SimpleRPCClient simpleRPCClient = new SimpleRPCClient("127.0.0.1", 4444);
//        // 把这个客户端传入代理客户端
//        RPCClientProxy rpcClientProxy = new RPCClientProxy(simpleRPCClient);
//        // 代理客户端根据不同的服务，获得一个代理类， 并且这个代理类的方法以或者增强（封装数据，发送请求）
//        UserService userService = rpcClientProxy.getProxy(UserService.class);
//
//        // 调用方法
//        User userByUserId2 = userService.getUserByUserId(4);
//        System.out.println("从服务端得到的user为：" + userByUserId2);
//
//        Integer insert = userService.insert(new User().builder().userName("myrpc4").id(4).sex("nice").build());
//        System.out.println("向服务端插入数据："+insert);
//
//        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
//        Blog blogById = blogService.getBlogById(44);
//        System.out.println("从服务端得到的blog为：" + blogById);
    }
}
