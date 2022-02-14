package com.weng.myrpc5.codec;

import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/10 16:28
 */
@AllArgsConstructor
public enum  MessageType {
    REQUEST(0), RESPONSE(1);
    private int code;
    public int getCode() {
        return code;
    }
}
