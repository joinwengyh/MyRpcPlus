package com.weng.myrpc1.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Hua
 * @Date: 2022/1/24 16:18
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    // 客户端和服务端共有的
    private  Integer id;
    private  String userName;
    private  String sex;
}
