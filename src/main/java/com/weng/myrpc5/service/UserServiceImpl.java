package com.weng.myrpc5.service;

import com.weng.myrpc5.common.User;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:45
 */
public class UserServiceImpl implements UserService {
    // 模拟从数据库中取用户的行为
    @Override
    public User getUserByUserId(Integer id) {
        User user = new User().builder().id(id).userName("myrpc5").sex("nice").build();
        System.out.println("客户端查询了myrpc5--->"+id+"用户");
        return  user;
    }

    @Override
    public Integer insert(User user) {
        System.out.println("客户端插入数据成功："+user);
        return 1;
    }
}
