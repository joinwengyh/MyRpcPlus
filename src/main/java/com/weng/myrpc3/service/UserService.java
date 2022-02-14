package com.weng.myrpc3.service;

import com.weng.myrpc3.common.User;

/**
 * @Author Hua
 * @Date: 2022/1/27 10:47
 */
public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);

    Integer insert(User user);
}
