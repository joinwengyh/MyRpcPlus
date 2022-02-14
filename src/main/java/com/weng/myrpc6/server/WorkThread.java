package com.weng.myrpc6.server;

import com.weng.myrpc6.common.RPCRequest;
import com.weng.myrpc6.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author Hua
 * @Date: 2022/2/13 14:05
 *     这里负责解析得到的request请求，执行服务方法，返回给客户端
 *  * 1. 从request得到interfaceName 2. 根据interfaceName在serviceProvide Map中获取服务端的实现类
 *  * 3. 从request中得到方法名，参数， 利用反射执行服务中的方法 4. 得到结果，封装成response，写入socket
 */
@AllArgsConstructor
public class WorkThread implements Runnable{
    private Socket socket;
    private ServiceProvider serviceProvider;

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             RPCRequest rpcRequest = (RPCRequest) ois.readObject();
            RPCResponse rpcResponse = getResponse(rpcRequest);
                oos.writeObject(rpcResponse);
                oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private RPCResponse getResponse(RPCRequest rpcRequest) {
        String interfaceName = rpcRequest.getInterfaceName();
        Object service = serviceProvider.getService(interfaceName);
        Method method = null;
        try {
             method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
            Object invoke = method.invoke(service, rpcRequest.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("方法执行错误");
            return RPCResponse.fail();
        }

    }
}
