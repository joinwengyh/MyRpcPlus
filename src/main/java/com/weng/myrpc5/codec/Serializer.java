package com.weng.myrpc5.codec;

import com.weng.myrpc5.codec.JsonSerializer;

/**
 * @Author Hua
 * @Date: 2022/2/10 15:43
 */
public interface Serializer {
   byte[] serialize(Object object);

   Object deserialize(byte[] bytes, int messageType);

    int getType();

    static Serializer getSerializerByCode(int code) {
        switch (code) {
            case 0:
                return new ObjectSerializer();

            case 1:
                return new JsonSerializer();

            default:
                return null;
        }
    }
}
