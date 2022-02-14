package com.weng.myrpc2.client;

import com.weng.myrpc2.common.RPCRequest;
import com.weng.myrpc2.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author Hua
 * @Date: 2022/1/26 15:52
 */
public class IOClient {

    // 客户端发起一次请求调用，Socket建立连接，发起请求Request，得到响应Response
    // 这里的request是封装好的，不同的service需要进行不同的封装， 客户端只知道Service接口
    // 需要一层动态代理根据反射封装不同的Service

    public static RPCResponse sendRequest(String host, int port, RPCRequest request) {
        try {
            Socket socket = new Socket(host,port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(request);
            oos.flush();
            RPCResponse response = (RPCResponse)ois.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
