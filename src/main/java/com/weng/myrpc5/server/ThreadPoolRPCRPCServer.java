package com.weng.myrpc5.server;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Hua
 * @Date: 2022/2/10 14:17
 */
@AllArgsConstructor
public class ThreadPoolRPCRPCServer implements RPCServer{
    private ThreadPoolExecutor threadPoolExecutor;
    private ServiceProvider serviceProvider;

    // 默认构造函数
    public ThreadPoolRPCRPCServer(ServiceProvider serviceProvider) {
        threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        this.serviceProvider = serviceProvider;
    }

    // 自定义构造函数
    public ThreadPoolRPCRPCServer(ServiceProvider serviceProvider,int corePoolSize,int maximumPoolSize,
        long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue) {
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit, workQueue);
        this.serviceProvider = serviceProvider;
    }
    @Override
    public void start(int port) {
        System.out.println("myrpc5线程池版服务端启动了");
        try {
            ServerSocket serverSocket = new ServerSocket();
            while (true) {
                Socket socket = serverSocket.accept();
                threadPoolExecutor.execute(new WorkThread(socket,serviceProvider));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
