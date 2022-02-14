package com.weng.myrpc1.service;

import com.weng.myrpc1.common.User;

/**
 * @Author Hua
 * @Date: 2022/1/24 16:37
 */
public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User  getUserByUserId(Integer id);
    // 给这个服务增加一个功能
    Integer insertUserId(User user);
}
