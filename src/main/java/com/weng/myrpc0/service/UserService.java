package com.weng.myrpc0.service;


import com.weng.myrpc0.common.User;

/**
 * @Author Hua
 * @Date: 2022/1/24 9:59
 */
public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);
}
