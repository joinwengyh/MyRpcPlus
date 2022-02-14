package com.weng.myrpc6.service;

import com.weng.myrpc6.common.User;

/**
 * @Author Hua
 * @Date: 2022/2/12 14:32
 */
public class UserServiceImpl implements UserService{
    @Override
    public User getUserByUserId(Integer id) {
        User user = new User().builder().id(id).userName("myrpc6").sex("nice").build();
        System.out.println("客户端查询了UserServiceImpl--->"+id+"用户");
        return user ;
    }

    @Override
    public Integer insert(User user) {
        System.out.println("UserServiceImpl客户端插入数据成功："+user);
        return 1;
    }
}
