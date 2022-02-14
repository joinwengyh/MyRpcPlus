package com.weng.myrpc6.register;

import org.apache.zookeeper.server.admin.Commands;

import java.net.InetSocketAddress;

/**
 * @Author Hua
 * @Date: 2022/2/14 9:19
 */
// 服务注册接口，两大基本功能，注册：保存服务与地址。 查询：根据服务名查找地址
public interface ServiceRegister {
    void register(String serviceName, InetSocketAddress inetSocketAddress);

    InetSocketAddress serviceDiscovery(String serviceName);
}
