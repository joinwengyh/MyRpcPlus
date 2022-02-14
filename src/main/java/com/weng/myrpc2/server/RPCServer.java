package com.weng.myrpc2.server;

/**
 * @Author Hua
 * @Date: 2022/1/26 15:19
 */
public interface RPCServer {
    public void start(int port);

    public  void stop();
}
