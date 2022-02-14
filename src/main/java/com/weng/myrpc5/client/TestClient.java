package com.weng.myrpc5.client;

import com.weng.myrpc5.common.Blog;
import com.weng.myrpc5.common.User;
import com.weng.myrpc5.service.BlogService;
import com.weng.myrpc5.service.UserService;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:47
 */
public class TestClient {
    public static void main(String[] args) {



        NettyRPCClient nettyRPCClient = new NettyRPCClient();
        RPCClientProxy rpcClientProxy = new RPCClientProxy(nettyRPCClient);
        UserService userService = rpcClientProxy.getProxy(UserService.class);
        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);

//         调用方法
        System.out.println("--------------------");
        User userByUserId = userService.getUserByUserId(5);
        System.out.println("从服务端得到的user为：" + userByUserId);

        Integer insert = userService.insert(new User().builder().userName("myrpc5").id(55).sex("nice").build());
        System.out.println("向服务端插入数据："+insert);

        Blog blogById = blogService.getBlogById(555);
        System.out.println("从服务端得到的blog为：" + blogById);




        //------------------------------------------------------------------------

//         构建一个使用java Socket/ netty/....传输的客户端
//        SimpleRPCClient simpleRPCClient = new SimpleRPCClient();
//        RPCClientProxy rpcClientProxy = new RPCClientProxy(simpleRPCClient);
//        UserService userService = rpcClientProxy.getProxy(UserService.class);
//        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
//
//
//        // 调用方法
//        User userByUserId = userService.getUserByUserId(5);
//        System.out.println("从服务端得到的user为：" + userByUserId);
//
//        Integer insert = userService.insert(new User().builder().userName("myrpc5").id(55).sex("nice").build());
//        System.out.println("向服务端插入数据："+insert);
//
//        Blog blogById = blogService.getBlogById(555);
//        System.out.println("从服务端得到的blog为：" + blogById);

    }
}
