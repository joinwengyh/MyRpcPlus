package com.weng.myrpc3.server;

/**
 * @Author Hua
 * @Date: 2022/1/27 10:57
 */
public interface RPCServer {
    void start(int port);

    void stop();
}
