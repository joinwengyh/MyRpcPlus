package com.weng.myrpc6.codec;

import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/13 20:57
 */
@AllArgsConstructor
public enum MessageType {
    REQUEST(0),RESPONSE(1);
    private int code;
    public int getCode() {
        return code;
    }
}
