package org.example.alvin.study.network.netty.embedded;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author AlvinPower
 * @date 2021/7/5
 */
class FixedLengthFrameDecoderTest {

  @Test
  void testFixedLengthMessageDecoder() {
    ByteBuf buf = Unpooled.buffer();
    for (int i = 0; i < 9; i++) {
      buf.writeByte(i);
    }
    ByteBuf input = buf.duplicate();
    EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
    Assertions.assertFalse(channel.writeInbound(input.readBytes(1)));
    Assertions.assertNull(channel.readInbound());
    Assertions.assertFalse(channel.writeInbound(input.readBytes(1)));
    Assertions.assertNull(channel.readInbound());
    // writeInbound 返回值代表 readInbound() 能否从该 channel 中读取到结果. 只有写入长度达到固定长度，才会触发 channel 的 flush 操作
    Assertions.assertTrue(channel.writeInbound(input.readBytes(1)));
    Assertions.assertNotNull(channel.readInbound());

    // buf 中最多只能再读取6个字节的数据，如果超出写入字节总数，会抛异常 java.lang.IndexOutOfBoundsException
    Assertions.assertTrue(channel.writeInbound(input.readBytes(6)));
    Assertions.assertNotNull(channel.readInbound());
    channel.finish();
  }
}
