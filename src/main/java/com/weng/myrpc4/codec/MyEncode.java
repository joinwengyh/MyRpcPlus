package com.weng.myrpc4.codec;

import com.weng.myrpc4.common.RPCRequest;
import com.weng.myrpc4.common.RPCResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

import java.awt.*;

/**
 * @Author Hua
 * @Date: 2022/2/8 16:29
 *    依次按照自定义的消息格式写入，传入的数据为request或者response
 *  * 需要持有一个serialize器，负责将传入的对象序列化成字节数组
 */
@AllArgsConstructor
public class MyEncode extends MessageToByteEncoder {
    private Serializer serializer;
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        System.out.println("MyEncode---->"+msg.getClass());
        // 写入消息类型

        //instanceof是Java中的二元运算符，左边是对象，右边是类；
        // 当对象是右边类或子类所创建对象时，返回true；否则，返回false。
        if (msg instanceof RPCRequest) {
            out.writeShort(MessageType.REQUEST.getCode());
        } else if (msg instanceof RPCResponse) {
            out.writeShort(MessageType.RESPONSE.getCode());
        }
        // 写入序列化方式
        out.writeShort(serializer.getType());
        // 得到序列化数组
        byte[] serialize = serializer.serialize(msg);
        // 写入长度
        out.writeInt(serialize.length);
        // 写入序列化字节数组
        out.writeBytes(serialize);
    }
}
