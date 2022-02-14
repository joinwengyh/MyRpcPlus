package com.weng.myrpc2.service;

import com.weng.myrpc2.common.Blog;

/**
 * @Author Hua
 * @Date: 2022/1/26 14:49
 */
public interface BlogService {
    Blog getBlogByBlogId(Integer id);
}
