package com.weng.myrpc5.service;

import com.weng.myrpc5.common.User;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:43
 */
public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
   User getUserByUserId(Integer id);

    Integer insert(User user);
}
