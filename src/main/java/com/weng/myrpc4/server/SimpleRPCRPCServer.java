package com.weng.myrpc4.server;

import com.weng.myrpc4.common.RPCRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Hua
 * @Date: 2022/2/7 21:50
 *
 * 这个实现类代表着java原始的BIO监听模式，来一个任务，就new一个线程去处理
 * 处理任务的工作见WorkThread中
 */
@NoArgsConstructor
public class SimpleRPCRPCServer implements RPCServer{
    // 存着服务接口名-> service对象的map
    private ServiceProvider serviceProvider;

    public SimpleRPCRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("SimpleRPCRPCServer服务端启动了");
            // BIO的方式监听Socket
            while (true) {
                Socket socket = serverSocket.accept();
                // 开启一个新线程去处理
                new Thread(new WorkThread(socket,serviceProvider)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        System.out.println("服务器启动失败");
    }
}
