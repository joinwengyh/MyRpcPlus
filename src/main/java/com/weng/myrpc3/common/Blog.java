package com.weng.myrpc3.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Hua
 * @Date: 2022/1/27 10:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
    private Integer id;
    private Integer useId;
    private String title;
}
