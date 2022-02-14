package com.weng.myrpc6.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Hua
 * @Date: 2022/2/12 13:27
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RPCResponse implements Serializable {
    private int code;
    private String message;
    // 更新,这里我们需要加入这个，不然用其它序列化方式（除了java Serialize）得不到data的type
    private Class<?> dataType;
    private Object data;

    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).message("成功").data(data).dataType(data.getClass()).build();
    }

    public static RPCResponse fail() {
        return RPCResponse.builder().code(500).message("服务器发生错误").build();
    }
}
