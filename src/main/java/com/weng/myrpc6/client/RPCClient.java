package com.weng.myrpc6.client;

import com.weng.myrpc6.common.RPCRequest;
import com.weng.myrpc6.common.RPCResponse;

/**
 * @Author Hua
 * @Date: 2022/2/12 15:21
 */
public interface RPCClient {
   RPCResponse sendRequest(RPCRequest rpcRequest);
}
