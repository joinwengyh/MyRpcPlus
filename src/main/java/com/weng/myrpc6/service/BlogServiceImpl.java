package com.weng.myrpc6.service;

import com.weng.myrpc6.common.Blog;

/**
 * @Author Hua
 * @Date: 2022/2/12 14:55
 */
public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogByBlogId(Integer id) {
        Blog blog = new Blog().builder().id(id).userId(6).title("myrpc6的博客").build();
        System.out.println("客户端查询了BlogServiceImpl-->"+id+"--myrpc6的博客");
        return blog;

    }
}
