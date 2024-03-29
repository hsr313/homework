package com.lagou.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> clazz;
    private Serializer serializer;

    public RpcEncoder(Class<?> clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }

    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        if(clazz != null && clazz.isInstance(msg)){
            //使用json序列化的方式将对象转换为byte
            byte[] bytes = serializer.serialize(msg);
            //先将消息长度写入，也就是消息头
            byteBuf.writeInt(bytes.length);
            //消息体中包含我们要发送的数据
            byteBuf.writeBytes(bytes);
        }
    }
}
