package com.learning.netty.codec2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 12
 **/
public class DefaultDecoder extends ByteToMessageDecoder {

    private final static int HEADER_LENGTH = 13;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < HEADER_LENGTH) {
            return;
        }
        in.markReaderIndex();

        long id = in.readLong();
        byte type = in.readByte();
        int length = in.readInt();
        if(in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }

        byte[] tmp = new byte[length];
        in.readBytes(tmp);
        Object obj = HessianSerializUtil.deserialize(tmp);
        out.add(obj);
    }
}