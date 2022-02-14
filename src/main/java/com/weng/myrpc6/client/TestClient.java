package com.weng.myrpc6.client;

import com.weng.myrpc6.common.Blog;
import com.weng.myrpc6.common.User;
import com.weng.myrpc6.service.BlogService;
import com.weng.myrpc6.service.UserService;

/**
 * @Author Hua
 * @Date: 2022/2/12 15:16
 */
public class TestClient {
    public static void main(String[] args) {
        //简单版客户端
//        SimpleRPCClient simpleRPCClient = new SimpleRPCClient();
//        RPCClientProxy rpcClientProxy = new RPCClientProxy(simpleRPCClient);

        //Netty版本客户端
        NettyRPCClient nettyRPCClient = new NettyRPCClient();
        RPCClientProxy rpcClientProxy = new RPCClientProxy(nettyRPCClient);

        UserService userService = rpcClientProxy.getProxy(UserService.class);
        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);



//         调用方法
        User userByUserId = userService.getUserByUserId(6);
        System.out.println("从服务端得到的user为：" + userByUserId);

        Integer insert = userService.insert(new User().builder().userName("myrpc6").id(66).sex("nice").build());
        System.out.println("向服务端插入数据："+insert);



        Blog blogById = blogService.getBlogByBlogId(666);
        System.out.println("从服务端得到的blog为：" + blogById);
    }
}
