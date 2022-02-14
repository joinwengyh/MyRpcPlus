package com.weng.myrpc5.codec;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * @Author Hua
 * @Date: 2022/2/10 15:47
 */
public class ObjectSerializer implements Serializer{
    // 利用java IO     对象 -> 字节数组
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
    // 从字节数组反序列化成消息, 使用java自带序列化方式不用messageType也能得到相应的对象（序列化字节数组里包含类信息）
    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object object = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    // 0 代表java原生序列化器
    @Override
    public int getType() {
        return 0;
    }
}
