package com.weng.myrpc4.server;

import com.weng.myrpc4.common.RPCRequest;
import com.weng.myrpc4.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Hua
 * @Date: 2022/2/8 14:57
 *    因为是服务器端，我们知道接受到请求格式是RPCRequest
 *  * Object类型也行，强制转型就行
 */
@AllArgsConstructor
public class NettyRPCServerHandler extends SimpleChannelInboundHandler<RPCRequest> {
    private ServiceProvider serviceProvider;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRequest rpcRequest) throws Exception {
        System.out.println("NettyRPCServerHandler---->"+rpcRequest);
        RPCResponse response = getResponse(rpcRequest);
        channelHandlerContext.writeAndFlush(response);
        channelHandlerContext.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

     RPCResponse getResponse(RPCRequest rpcRequest) {

        String interfaceName = rpcRequest.getInterfaceName();
        Object service = serviceProvider.getService(interfaceName);
        Method method = null;
        try {
           method= service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
            Object invoke = method.invoke(service, rpcRequest.getParams());
           return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("方法执行错误");
            return RPCResponse.fail();
        }
    }
}
