package com.weng.myrpc3.service;

import com.weng.myrpc3.common.Blog;

/**
 * @Author Hua
 * @Date: 2022/1/27 10:53
 */
public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("优秀的博客").useId(888).build();
        System.out.println("客户端查询了"+id+"的博客");
        return blog;
    }
}
