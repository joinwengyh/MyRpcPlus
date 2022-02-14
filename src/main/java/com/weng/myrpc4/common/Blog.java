package com.weng.myrpc4.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Hua
 * @Date: 2022/2/7 15:31
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Blog implements Serializable {
    private Integer id;
    private Integer userId;
    private String title;
}
