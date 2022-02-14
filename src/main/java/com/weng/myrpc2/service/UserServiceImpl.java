package com.weng.myrpc2.service;

import com.weng.myrpc2.common.User;

/**
 * @Author Hua
 * @Date: 2022/1/26 14:39
 */
public class UserServiceImpl implements UserService {


    @Override
    public User getUserByUserId2(Integer id) {
        User user = User.builder().id(id).userName("hua").sex("nan").build();
        return user;
    }

    @Override
    public Integer insertUser2(com.weng.myrpc1.common.User user) {
        System.out.println("插入数据成功："+user);
        return 1;
    }


}
