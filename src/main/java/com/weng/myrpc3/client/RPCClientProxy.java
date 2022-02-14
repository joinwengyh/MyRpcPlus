package com.weng.myrpc3.client;

import com.weng.myrpc3.common.RPCRequest;
import com.weng.myrpc3.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author Hua
 * @Date: 2022/1/27 11:20
 */
@AllArgsConstructor
public class RPCClientProxy implements InvocationHandler {
    private RPCClient rpcClient;

    // jdk 动态代理， 每一次代理对象调用方法，会经过此、方法增强（反射获取request对象，socket发送至客户端）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // request的构建，使用了lombok中的builder，代码简洁
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes()).build();
        //数据传输
        RPCResponse response = rpcClient.sendRequest(request);
        return response.getData();
    }

         <T>T getProxy(Class<T> clazz) {
            Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
            return (T) o;

    }
}
