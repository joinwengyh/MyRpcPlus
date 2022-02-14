package com.weng.myrpc5.client;

import com.weng.myrpc5.common.RPCRequest;
import com.weng.myrpc5.common.RPCResponse;

/**
 * @Author Hua
 * @Date: 2022/2/10 10:50
 */
public interface RPCClient {
    RPCResponse sentRPCRequest(RPCRequest rpcRequest);
}
