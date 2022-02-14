package com.weng.myrpc3.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Hua
 * @Date: 2022/1/27 10:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    // 客户端和服务端共有的，模拟RPC中传输的信息
    private Integer id;
    private String userName;
    private String sex;
}
