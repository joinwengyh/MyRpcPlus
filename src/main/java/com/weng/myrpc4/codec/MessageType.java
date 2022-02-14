package com.weng.myrpc4.codec;

import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/8 16:41
 */
@AllArgsConstructor
public enum  MessageType {
    REQUEST(0), RESPONSE(1);
    private int code;

    public int getCode() {
        return code;
    }
}
