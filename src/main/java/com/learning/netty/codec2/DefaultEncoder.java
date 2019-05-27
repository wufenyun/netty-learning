package com.learning.netty.codec2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 12
 **/
public class DefaultEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] content = HessianSerializUtil.serialize(msg);
        Request request = new Request();
        request.setId(1);
        request.setMessageType((byte) 1);
        request.setLength(content.length);
        out.writeLong(request.getId());
        out.writeByte(request.getMessageType());
        out.writeInt(request.getLength());

        out.writeBytes(content);
    }
}