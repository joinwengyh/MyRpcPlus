package com.weng.myrpc6.server;

/**
 * @Author Hua
 * @Date: 2022/2/12 17:19
 */
public interface RPCServer {
    void start(int port);

    void stop();
}
