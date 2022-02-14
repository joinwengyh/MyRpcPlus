package com.weng.myrpc4.service;

import com.weng.myrpc4.common.Blog;

/**
 * @Author Hua
 * @Date: 2022/2/7 15:47
 */
public class BlogServiceImpl implements BlogService{
    @Override
    public Blog getBlogById(Integer id) {
        // 模拟从数据库中取用户的行为
        Blog blog = new Blog().builder().id(id).userId(4).title("myrpc4的博客").build();
        System.out.println("客户端查询了"+id+"--myrpc4的博客");
        return blog;
    }
}
