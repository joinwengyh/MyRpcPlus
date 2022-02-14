package com.weng.myrpc5.server;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:56
 */
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRPCRPCServer implements RPCServer {
    private ServiceProvider serviceProvider;
    private int port;

    public  SimpleRPCRPCServer(ServiceProvider serviceProvider) {

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
