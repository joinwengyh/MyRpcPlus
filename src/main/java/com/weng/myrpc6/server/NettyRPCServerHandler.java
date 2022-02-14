package com.weng.myrpc6.server;

import com.weng.myrpc6.common.RPCRequest;
import com.weng.myrpc6.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Hua
 * @Date: 2022/2/13 16:47
 *    因为是服务器端，我们知道接受到请求格式是RPCRequest
 *  * Object类型也行，强制转型就行
 */
@AllArgsConstructor
public class NettyRPCServerHandler extends SimpleChannelInboundHandler<RPCRequest> {
    private ServiceProvider serviceProvider;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCRequest rpcRequest) throws Exception {
        RPCResponse response = getResponse(rpcRequest);
        ctx.writeAndFlush(response);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private RPCResponse getResponse(RPCRequest rpcRequest) {
        // 得到服务名
        String interfaceName = rpcRequest.getInterfaceName();
        // 得到服务端相应服务实现类
        Object service = serviceProvider.getService(interfaceName);
        // 反射调用方法
        Method method = null;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
            Object invoke = method.invoke(service, rpcRequest.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return RPCResponse.fail();
        }
    }


}
