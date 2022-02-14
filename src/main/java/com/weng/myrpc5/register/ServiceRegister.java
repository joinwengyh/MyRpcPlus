package com.weng.myrpc5.register;

import java.net.InetSocketAddress;

/**
 * @Author Hua
 * @Date: 2022/2/11 11:28
 */
// 服务注册接口，两大基本功能，注册：保存服务名与地址。 查询：根据服务名查找地址
public interface ServiceRegister {
    void register(String serviceName, InetSocketAddress socketAddress);

    InetSocketAddress serviceDiscovery(String serviceName);
}
