package com.weng.myrpc5.codec;

import com.weng.myrpc5.common.RPCRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * @Author Hua
 * @Date: 2022/2/10 16:23
 */
@AllArgsConstructor
public class MyEncode extends MessageToByteEncoder {
    private Serializer serializer;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        System.out.println("MyEncode---->"+o.getClass());

        // 写入消息类型

        //instanceof是Java中的二元运算符，左边是对象，右边是类；
        // 当对象是右边类或子类所创建对象时，返回true；否则，返回false。
        if (o instanceof RPCRequest) {
            byteBuf.writeShort(MessageType.REQUEST.getCode());
        } else {
            byteBuf.writeShort(MessageType.RESPONSE.getCode());
        }
        // 写入序列化方式
        byteBuf.writeShort(serializer.getType());
        // 得到序列化数组
        byte[] bytes = serializer.serialize(o);
        // 写入长度
        byteBuf.writeShort(bytes.length);
        // 写入序列化字节数组
        byteBuf.writeBytes(bytes);
    }
}
