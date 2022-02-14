package com.weng.myrpc6.service;

import com.weng.myrpc6.common.Blog;

/**
 * @Author Hua
 * @Date: 2022/2/12 14:30
 */
public interface BlogService {
    Blog getBlogByBlogId(Integer id);
}
