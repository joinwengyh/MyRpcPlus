package com.weng.myrpc1.client;

import com.weng.myrpc1.common.User;
import com.weng.myrpc1.service.UserService;

/**
 * @Author Hua
 * @Date: 2022/1/25 11:06
 */
public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1",8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        // 服务的方法1
        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);

        // 服务的方法2
        User user = User.builder().id(100).userName("陈冠希").sex("nan").build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("向服务端插入数据："+integer);
    }
}
