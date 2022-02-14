package com.weng.myrpc6.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * @Author Hua
 * @Date: 2022/2/14 10:31
 * 随机负载均衡
 */
public class RandomLoadBalance implements LoadBalance {

    @Override
    public String balance(List<String> addressList) {
        Random random = new Random();
        //带参的nextInt(int x)则会生成一个范围在0~x（不包含X）内的任意正整数
        int choose = random.nextInt(addressList.size());//(0-size-1)
        System.out.println("随机负载均衡选择了" + choose + "服务器");
        return addressList.get(choose);
    }
}
