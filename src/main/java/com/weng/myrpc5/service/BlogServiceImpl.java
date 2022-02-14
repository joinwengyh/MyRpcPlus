package com.weng.myrpc5.service;

import com.weng.myrpc5.common.Blog;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:40
 */
public class BlogServiceImpl implements BlogService{

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = new Blog().builder().id(id).userId(5).title("myrpc5的博客").build();
        System.out.println("客户端查询了"+id+"--myrpc5的博客");
        return blog;
    }
}
