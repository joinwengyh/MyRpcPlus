package com.weng.myrpc4.client;

import com.weng.myrpc4.common.RPCRequest;
import com.weng.myrpc4.common.RPCResponse;

/**
 * @Author Hua
 * @Date: 2022/2/7 16:14
 */
public interface RPCClient {
   RPCResponse sendRequest(RPCRequest rpcRequest);
}
