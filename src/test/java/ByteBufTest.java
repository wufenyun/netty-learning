/*
import io.netty.buffer.*;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.junit.Test;

*/
/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-03 15
 **//*

public class ByteBufTest {

    @Test
    public void test() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes("hellllllllllllll".getBytes());
        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.readerIndex());
       */
/* byteBuf.readByte();
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.markReaderIndex());
        byteBuf.readByte();
        byteBuf.readByte();
        byteBuf.readByte();
        System.out.println(byteBuf.readerIndex());
        byteBuf.resetReaderIndex();
        System.out.println(byteBuf.readerIndex());
        byteBuf.readerIndex(0);
        System.out.println(byteBuf.readerIndex());*//*


        ByteBuf result = byteBuf.readRetainedSlice(10);
        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.readerIndex());

        System.out.println(result.readableBytes());
        System.out.println(result.readerIndex());
    }

    @Test
    public void caculate() {
        ByteBuf cacu = ByteBufAllocator.DEFAULT.buffer();
        cacu.writeBytes("aaaaaaaaaa".getBytes());
        cacu.readRetainedSlice(5);
        System.out.println(cacu.readerIndex(0));

        ByteBuf in = ByteBufAllocator.DEFAULT.buffer();
        in.writeBytes("bbbbbbbbbbbb".getBytes());

        ByteToMessageDecoder.Cumulator caculator = ByteToMessageDecoder.MERGE_CUMULATOR;
        ByteBuf re = caculator.cumulate(new PooledByteBufAllocator(),cacu,in);

        System.out.println(re.readableBytes());
        System.out.println(re.readerIndex());
        System.out.println(re.writerIndex());

        byte[] temp = new byte[(re.readableBytes())];
        re.readBytes(temp);
        System.out.println(new String(temp));
    }

    @Test
    public void index() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes("hellllllllllllll".getBytes());
        System.out.println(byteBuf.readableBytes());
        byteBuf.getByte(1);
        System.out.println(byteBuf.readableBytes());
        byteBuf.readByte();
        System.out.println(byteBuf.readableBytes());
    }

}*/
