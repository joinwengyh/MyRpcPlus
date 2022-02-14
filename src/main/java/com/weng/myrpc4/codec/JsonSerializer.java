package com.weng.myrpc4.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weng.myrpc4.common.RPCRequest;
import com.weng.myrpc4.common.RPCResponse;

/**
 * @Author Hua
 * @Date: 2022/2/8 15:59
 *       由于json序列化的方式是通过把对象转化成字符串，丢失了Data对象的类信息，所以deserialize需要
 *  *   了解对象对象的类信息，根据类信息把JsonObject -> 对应的对象
 */
public class JsonSerializer implements Serializer {

    @Override
    public byte[] serialize(Object obj) {
        byte[] bytes = JSONObject.toJSONBytes(obj);
        return bytes;
    }

    //由于json序列化的方式是通过把对象转化成字符串，丢失了Data对象的类信息，所以deserialize需要
    //      了解对象对象的类信息，根据类信息把JsonObject -> 对应的对象
    @Override
    public Object deserializer(byte[] bytes, int messageType) {
        Object obj = null;
        // 传输的消息分为request与response
        switch (messageType) {
            case 0:
                RPCRequest request = JSON.parseObject(bytes, RPCRequest.class);
                // 修bug 参数为空 直接返回
                if(request.getParams() == null) return request;
                Object[] objects = new Object[request.getParams().length];
                // 把json字串转化成对应的对象， fastjson可以读出基本数据类型，不用转化
                for (int i = 0; i < objects.length; i++) {
                    Class<?> paramsType = request.getParamsTypes()[i];
                    if (!paramsType.isAssignableFrom(request.getParams()[i].getClass())) {
                        objects[i] = JSONObject.toJavaObject((JSONObject) request.getParams()[i], request.getParamsTypes()[i]);
                    } else {
                        objects[i] = request.getParams()[i];
                    }
                }
                request.setParams(objects);
                obj = request;
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


