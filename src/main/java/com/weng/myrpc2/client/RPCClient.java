package com.weng.myrpc2.client;

import com.weng.myrpc1.common.User;
import com.weng.myrpc2.common.Blog;
import com.weng.myrpc2.service.BlogService;
import com.weng.myrpc2.service.UserService;

/**
 * @Author Hua
 * @Date: 2022/1/26 15:41
 */
public class RPCClient {
    public static void main(String[] args) {
        RPCClientProxy rpcClientProxy = new RPCClientProxy("127.0.0.1", 8889);
        UserService userService = rpcClientProxy.getProxy(UserService.class);

        com.weng.myrpc2.common.User userByUserId = userService.getUserByUserId2(3);
        System.out.println("从服务端得到的user为："+userByUserId);

            Integer insert = userService.insertUser2(User.builder().sex("NAN").id(8).userName("666").build());
        System.out.println("向服务端插入数据："+insert);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blogByBlogId = blogService.getBlogByBlogId(4);
        System.out.println("从服务端得到的blog为：" + blogByBlogId);
    }
}
