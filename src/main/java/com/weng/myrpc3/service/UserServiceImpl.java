package com.weng.myrpc3.service;

import com.weng.myrpc3.common.User;

/**
 * @Author Hua
 * @Date: 2022/1/27 10:49
 */
public class UserServiceImpl implements UserService{
    @Override
    public User getUserByUserId(Integer id) {
        User user = User.builder().id(888).sex("man").userName("大大大").build();
        System.out.println("客户端查询了"+id+"的用户");
        return user;
    }

    @Override
    public Integer insert(User user) {
        System.out.println("插入数据成功："+user);
        return 1;
    }
}
