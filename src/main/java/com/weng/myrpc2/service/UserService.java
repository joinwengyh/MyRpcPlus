package com.weng.myrpc2.service;

import com.weng.myrpc1.common.User;

/**
 * @Author Hua
 * @Date: 2022/1/26 14:38
 */
public interface UserService {
    com.weng.myrpc2.common.User getUserByUserId2(Integer id);

    Integer insertUser2(User user);
}
