package com.weng.myrpc4.service;

import com.weng.myrpc4.common.User;

/**
 * @Author Hua
 * @Date: 2022/2/7 15:41
 */
public class UserServiceImpl implements UserService{
    @Override
    public User getUserByUserId(Integer id) {
        // 模拟从数据库中取用户的行为
        User user = new User().builder().id(id).userName("myrpc4").sex("nice").build();
        System.out.println("客户端查询了myrpc4--->"+id+"用户");
        return  user;
    }

    @Override
    public Integer insert(User user) {
        System.out.println("客户端插入数据成功："+user);
        return 1;
    }
}
