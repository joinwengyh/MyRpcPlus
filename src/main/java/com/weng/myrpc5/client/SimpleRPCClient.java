package com.weng.myrpc5.client;

import com.weng.myrpc5.common.RPCRequest;
import com.weng.myrpc5.common.RPCResponse;
import com.weng.myrpc5.register.ServiceRegister;
import com.weng.myrpc5.register.ZkServiceRegister;
import lombok.AllArgsConstructor;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:48
 */
@AllArgsConstructor
public class SimpleRPCClient implements RPCClient{
    private String host;
    private int port;
    private ServiceRegister serviceRegister;

    public SimpleRPCClient() {
        // 初始化注册中心，建立连接
        this.serviceRegister = new ZkServiceRegister();
    }


    // 客户端发起一次请求调用，Socket建立连接，发起请求Request，得到响应Response
    // 这里的request是封装好的，不同的service需要进行不同的封装， 客户端只知道Service接口，
    // 需要一层动态代理根据反射封装不同的Service
    @Override
    public RPCResponse sentRPCRequest(RPCRequest rpcRequest) {
        // 从注册中心获取host，port
        InetSocketAddress address = serviceRegister.serviceDiscovery(rpcRequest.getInterfaceName());
         host = address.getHostName();
         port = address.getPort();
        try {
            Socket socket = new Socket(host,port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(rpcRequest);
            oos.flush();
            RPCResponse rpcResponse = (RPCResponse) ois.readObject();
            return rpcResponse;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
