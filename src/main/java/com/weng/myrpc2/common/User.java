package com.weng.myrpc2.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Hua
 * @Date: 2022/1/26 14:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    // 客户端和服务端共有的，模拟RPC中传输的信息
    private String userName;
    private Integer id;
    private String sex;
}
