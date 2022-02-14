package com.weng.myrpc2.service;

import com.weng.myrpc2.common.Blog;

/**
 * @Author Hua
 * @Date: 2022/1/26 14:50
 */
public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogByBlogId(Integer id) {
        Blog blog = Blog.builder().id(id).title("我的博客").useId(9527).build();
        System.out.println("客户端查询了"+id+"博客");
        return blog;
    }
}
