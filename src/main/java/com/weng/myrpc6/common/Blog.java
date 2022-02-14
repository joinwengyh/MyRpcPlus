package com.weng.myrpc6.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Random;

/**
 * @Author Hua
 * @Date: 2022/2/12 13:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blog implements Serializable {
    // 客户端和服务端共有的，模拟RPC中传输的信息
    private Integer id;
    private Integer userId;
    private String title;


}
