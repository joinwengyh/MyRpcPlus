package com.weng.myrpc5.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:35
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RPCRequest implements Serializable {
    // 服务类名，客户端只知道接口名，在服务端中用接口名指向实现类
    private String interfaceName;
    // 方法名
    private String methodName;
    // 参数列表
    private Object[] params;
    // 参数类型
    private Class<?>[] paramsTypes;

}