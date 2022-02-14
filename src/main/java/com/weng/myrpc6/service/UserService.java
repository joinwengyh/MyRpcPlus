package com.weng.myrpc6.service;

import com.weng.myrpc6.common.User;

/**
 * @Author Hua
 * @Date: 2022/2/12 14:01
 */
public interface UserService {
   User getUserByUserId(Integer id);

   Integer  insert(User user);
}
