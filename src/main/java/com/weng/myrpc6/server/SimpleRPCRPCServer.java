package com.weng.myrpc6.server;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Hua
 * @Date: 2022/2/12 17:28
 *    这个实现类代表着java原始的BIO监听模式，来一个任务，就new一个线程去处理
 *  * 处理任务的工作见WorkThread中
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
                new Thread(new WorkThread(socket,serviceProvider)).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    @Override
    public void stop() {

    }
}
