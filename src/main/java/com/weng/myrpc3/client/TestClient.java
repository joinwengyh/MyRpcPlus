package com.weng.myrpc3.client;

import com.weng.myrpc3.common.Blog;
import com.weng.myrpc3.common.User;
import com.weng.myrpc3.service.BlogService;
import com.weng.myrpc3.service.UserService;

/**
 * @Author Hua
 * @Date: 2022/1/27 11:22
 */
public class TestClient {
    public static void main(String[] args) {

//        //构建一个使用netty传输的客户端
//        NettyRPCClient nettyRPCClient = new NettyRPCClient("127.0.0.1",8888);
//        // 把这个客户端传入代理客户端
//        RPCClientProxy rpcClientProxy = new RPCClientProxy(nettyRPCClient);
//        // 代理客户端根据不同的服务，获得一个代理类， 并且这个代理类的方法以或者增强（封装数据，发送请求）
//        UserService userService = rpcClientProxy.getProxy(UserService.class);
//        // 调用方法
//        User user = userService.getUserByUserId(555);
//        System.out.println("从服务端得到的user为：" + user);
//
//        User man = User.builder().userName("3的username").id(666).sex("man").build();
//        Integer insert = userService.insert(man);
//        System.out.println("向服务端插入数据："+insert);
//
//
//        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
//        Blog blog = blogService.getBlogById(777);
//        System.out.println("从服务端得到的blog为：" +blog);


        //-------------------------------------------------------------------------------------------------


        // 构建一个使用java Socket传输的客户端
        SimpleRPCClient simpleRPCClient = new SimpleRPCClient("127.0.0.1", 9999);
        // 把这个客户端传入代理客户端
        RPCClientProxy rpcClientProxy = new RPCClientProxy(simpleRPCClient);
        // 代理客户端根据不同的服务，获得一个代理类， 并且这个代理类的方法以或者增强（封装数据，发送请求）
        UserService userService = rpcClientProxy.getProxy(UserService.class);
        // 调用方法
        User userByUserId = userService.getUserByUserId(1);
        System.out.println("simple版本userByUserId------"+userByUserId);

        Integer insert = userService.insert(User.builder().id(2).userName("w").sex("nan").build());
        System.out.println("simple版本insert------"+insert);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(3);
        System.out.println("simple版本blogById-----"+blogById);

    }
}
