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
  }
}
