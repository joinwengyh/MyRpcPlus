package com.weng.myrpc6.codec;

import java.io.*;

/**
 * @Author Hua
 * @Date: 2022/2/13 18:28
 */
public class ObjectSerializer implements Serializer{
    // 利用java IO 对象 -> 字节数组
    @Override
    public byte[] serialize(Object object) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
             bytes = bos.toByteArray();
             oos.close();
             bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    // 字节数组 -> 对象
    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object obj = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // 0 代表java原生序列化器
    @Override
    public int getType() {
        return 0;
    }
}
