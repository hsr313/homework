package com.lagou.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> clazz;
    private Serializer serializer;

    public RpcDecoder(Class<?> clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //因为之前编码的时候写入一个int型，4个字节来表示长度
        if(byteBuf.readableBytes()<4){
            return;
        }
        //标记一下当前的readIndex的位置
        byteBuf.markReaderIndex();
        //读取传送过来的消息长度，ByteBuf的read()方法会让其readIndex增加
        int dataLength = byteBuf.readInt();
        if (dataLength < 0) { // 我们读到的消息体长度为0，这是不应该出现的情况，这里出现这情况，关闭连接。
            channelHandlerContext.close();
        }
        if (byteBuf.readableBytes() < dataLength) { //读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex. 这个配合markReaderIndex使用的。把readIndex重置到mark的地方
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];  //  嗯，这时候，我们读到的长度，满足我们的要求了，把传送过来的数据，取出来吧~~
        byteBuf.readBytes(data);  //
        Object o = serializer.deserialize(clazz,data);  //将byte数据转化为我们需要的对象
        list.add(o);
    }
}
