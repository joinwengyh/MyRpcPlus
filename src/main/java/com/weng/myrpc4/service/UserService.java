package com.weng.myrpc4.service;


import com.weng.myrpc4.common.User;

/**
 * @Author Hua
 * @Date: 2022/2/7 15:36
 */
public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
   User getUserByUserId(Integer id);

   Integer insert(User user);
}
