package org.example.alvin.study.network.netty.embedded;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author AlvinPower
 * @date 2021/7/5
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {

  private final int frameLengthBytes;

  public FixedLengthFrameDecoder(int frameLengthBytes) {
    if (frameLengthBytes <= 0) {
      throw new IllegalArgumentException(
          "frameLength must be a positive integer: " + frameLengthBytes);
    }
    this.frameLengthBytes = frameLengthBytes;
  }

  @Override
  protected void decode(
      ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
      throws Exception {
    while (byteBuf.readableBytes() >= frameLengthBytes) {
      // 检查是否有足够的字节可以被读取以生成下一个帧
      ByteBuf buf = byteBuf.readBytes(frameLengthBytes);
      // 将该帧添加到已被解码的消息列表中
      list.add(buf);
    }
  }
}
