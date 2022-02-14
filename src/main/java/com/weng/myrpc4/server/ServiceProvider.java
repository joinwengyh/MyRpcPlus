package com.weng.myrpc4.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Hua
 * @Date: 2022/2/7 21:30
 *    存放服务接口名与服务端对应的实现类
 *  * 服务启动时要暴露其相关的实现类
 *  * 根据request中的interface调用服务端中相关实现类
 */
public class ServiceProvider {
    private Map<String, Object> interfaceProvider;

    public ServiceProvider() {
        this.interfaceProvider = new HashMap<>();
    }

    public void providerServiceInterface(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            interfaceProvider.put(anInterface.getName(), service);
        }
    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
