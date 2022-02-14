package com.weng.myrpc5.server;

import com.weng.myrpc5.register.ServiceRegister;
import com.weng.myrpc5.register.ZkServiceRegister;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Hua
 * @Date: 2022/2/10 11:05
 *      存放服务接口名与服务端对应的实现类
 * *  * 服务启动时要暴露其相关的实现类
 * *  * 根据request中的interface调用服务端中相关实现类
 *
 *    后面在这里新增了在zookeeper中注册的功能
 */
public class ServiceProvider {
    private Map<String, Object> interfaceProvider;

    private ServiceRegister serviceRegister;
    private String host;
    private int port;

    public ServiceProvider(String host,int port) {
        // 需要传入服务端自身的服务的网络地址
        this.host = host;
        this.port = port;
        this.interfaceProvider = new HashMap<>();
        this.serviceRegister = new ZkServiceRegister();
    }

    public void providerServiceInterface(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            // 本机的映射表
            interfaceProvider.put(anInterface.getName(), service);
            // 在注册中心注册服务
           serviceRegister.register(anInterface.getName(),new InetSocketAddress(host,port));
        }

    }

    public Object getService(String interfaceName) {
      return   interfaceProvider.get(interfaceName);
    }
}
