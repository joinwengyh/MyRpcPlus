package com.weng.myrpc1.server;

import com.weng.myrpc1.common.User;
import com.weng.myrpc1.service.UserService;

import java.util.Random;
import java.util.UUID;

/**
 * @Author Hua
 * @Date: 2022/1/24 16:41
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了"+id+"的用户");
        // 模拟从数据库中取用户的行为
        User user = User.builder().userName("华")
                .id(id)
                .sex("nan").build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功："+user);
        return 1;
    }
}
