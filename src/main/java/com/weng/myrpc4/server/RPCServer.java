package com.weng.myrpc4.server;

/**
 * @Author Hua
 * @Date: 2022/2/7 21:47
 */
public interface RPCServer {
    void start(int port);

    void stop();
}
