package com.weng.myrpc5.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author Hua
 * @Date: 2022/2/10 16:31
 */
public class MyDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 1. 读取消息类型
        short messageType = byteBuf.readShort();
        // 现在还只支持request与response请求
        if (messageType != MessageType.REQUEST.getCode() &&
                messageType != MessageType.RESPONSE.getCode()) {
            System.out.println("暂不支持此种数据");
            return;
        }
        // 2. 读取序列化的类型
       short serializerType= byteBuf.readShort();
        // 根据类型得到相应的序列化器
        Serializer serializerByCode = Serializer.getSerializerByCode(serializerType);
        if (serializerByCode==null)throw new RuntimeException("不存在对应的序列化器");

        // 3. 读取数据序列化后的字节长度
        short length = byteBuf.readShort();
        // 4. 读取序列化数组
       byte[] bytes= new byte[length];
        byteBuf.readBytes(bytes);
        // 用对应的序列化器解码字节数组
        Object deserialize = serializerByCode.deserialize(bytes, messageType);
        list.add(deserialize);
    }
}
