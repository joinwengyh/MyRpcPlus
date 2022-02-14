package com.weng.myrpc3.client;

import com.weng.myrpc3.common.RPCRequest;
import com.weng.myrpc3.common.RPCResponse;

/**
 * @Author Hua
 * @Date: 2022/1/27 11:10
 */
public interface RPCClient {
    RPCResponse sendRequest(RPCRequest rpcRequest);
}
