package com.weng.myrpc5.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weng.myrpc5.common.RPCRequest;
import com.weng.myrpc5.common.RPCResponse;

/**
 * @Author Hua
 * @Date: 2022/2/10 15:48
 *          由于json序列化的方式是通过把对象转化成字符串，丢失了Data对象的类信息，所以deserialize需要
 *  *  *   了解对象对象的类信息，根据类信息把JsonObject -> 对应的对象
 */
public class JsonSerializer implements Serializer{
    @Override
    public byte[] serialize(Object object) {
        byte[] bytes = JSONObject.toJSONBytes(object);
        return bytes;
    }

    //由于json序列化的方式是通过把对象转化成字符串，丢失了Data对象的类信息，所以deserialize需要
    //      了解对象对象的类信息，根据类信息把JsonObject -> 对应的对象
    //这种情况不会在其他序列化方式中出现，因为其他序列化方式是转换成字节数组，会记录对象的信息，
    // 而 JSON 方式本质上只是转换成 JSON 字符串，会丢失对象的类型信息。
    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object obj = null;
        // 传输的消息分为request与response
        switch (messageType) {
            case 0:
                RPCRequest rpcRequest = JSON.parseObject(bytes, RPCRequest.class);
                // 修bug 参数为空 直接返回
                if (rpcRequest.getParams()==null) return rpcRequest;
              Object[] objects=  new Object[rpcRequest.getParams().length];
                // 把json字串转化成对应的对象， fastjson可以读出基本数据类型，不用转化
//                这里由于使用JSON序列化和反序列化Object数组，无法保证反序列化后仍然为原实例类型
//                    需要重新判断处理
                for (int i = 0; i < objects.length; i++) {
                    Class<?> paramsType = rpcRequest.getParamsTypes()[i];
                    if (!paramsType.isAssignableFrom(rpcRequest.getParams()[i].getClass())) {
                        objects[i] = JSONObject.toJavaObject((JSONObject) rpcRequest.getParams()[i], rpcRequest.getParamsTypes()[i]);
                    } else {
                        objects[i] = rpcRequest.getParams()[i];
                    }
                }
                rpcRequest.setParams(objects);
                obj = rpcRequest;
                break;

            case 1:
                RPCResponse rpcResponse = JSON.parseObject(bytes, RPCResponse.class);
                Class<?> dataType = rpcResponse.getDataType();
                if (!dataType.isAssignableFrom(rpcResponse.getData().getClass())) {
                    rpcResponse.setData(JSONObject.toJavaObject((JSONObject)rpcResponse.getData(),dataType));
                }
                obj = rpcResponse;
                break;

            default:
                System.out.println("暂时不支持此种消息");
                throw new RuntimeException();
        }
        return obj;
    }
    //  1 代表着json序列化方式
    @Override
    public int getType() {
        return 1;
    }
}
