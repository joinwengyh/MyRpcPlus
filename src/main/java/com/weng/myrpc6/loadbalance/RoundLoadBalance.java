package com.weng.myrpc6.loadbalance;

import java.util.List;

/**
 * @Author Hua
 * @Date: 2022/2/14 10:35
 * 轮询负载均衡
 */
public class RoundLoadBalance implements LoadBalance {
    private int choose = -1;
    @Override
    public String balance(List<String> addressList) {
        choose++;
       choose= choose % addressList.size();
        System.out.println("轮询负载均衡选择了" + choose + "服务器");
        return addressList.get(choose);
    }


//    private int choose = 0;
//    @Override
//    public String balance(List<String> addressList) {
//        if (choose >= addressList.size()) {
//            choose = choose % addressList.size();
//        }
//        System.out.println("轮询负载均衡选择了" + choose + "服务器");
//        return addressList.get(choose++);
}
