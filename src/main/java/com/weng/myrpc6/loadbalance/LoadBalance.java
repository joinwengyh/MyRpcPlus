package com.weng.myrpc6.loadbalance;

import java.util.List;

/**
 * @Author Hua
 * @Date: 2022/2/14 10:30
 */
public interface LoadBalance {
    //给服务器地址列表，根据不同的负载均衡策略选择一个
    String balance(List<String> addressList);
}
