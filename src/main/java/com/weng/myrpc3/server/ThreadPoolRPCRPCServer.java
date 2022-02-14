package com.weng.myrpc3.server;

import com.weng.myrpc4.server.RPCServer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Hua
 * @Date: 2022/2/8 10:33
 *     线程池版服务端的实现
 *  * 处理任务的工作见WorkThread中
 */

public class ThreadPoolRPCRPCServer implements RPCServer {
    private ServiceProvider serviceProvider;
    private final ThreadPoolExecutor threadPoolExecutor;

    // 默认构造函数
    public ThreadPoolRPCRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    }

    //     自定义构造函数
    public ThreadPoolRPCRPCServer(ServiceProvider serviceProvider, int corePoolSize, int maximumPoolSize,
                                  long keepAliveTime, TimeUnit unit, ArrayBlockingQueue<Runnable> workQueue) {
        this.serviceProvider = serviceProvider;
        threadPoolExecutor=new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,
                unit,workQueue);
    }

    @Override
    public void start(int port) {
        System.out.println("myrpc3线程池版服务端启动了");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            threadPoolExecutor.execute(new WorkThread(socket,serviceProvider));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
