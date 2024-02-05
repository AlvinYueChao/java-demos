package org.example.alvin.study.network.netty.udp.broadcast.listener;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.alvin.study.network.netty.udp.broadcast.LogMsg;

/**
 * @author AlvinPower
 * @date 2021/7/15
 */
@Slf4j
public class LogMsgDecoder extends MessageToMessageDecoder<DatagramPacket> {

  @Override
  protected void decode(
      ChannelHandlerContext channelHandlerContext,
      DatagramPacket datagramPacket,
      List<Object> list) {
    ByteBuf buf = datagramPacket.content();
    long sendTime = buf.readLong();
    LocalDateTime sendTimeUTC =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(sendTime), ZoneId.of("UTC"));
    log.info("Received message at: {}", sendTimeUTC);
    long msgId = buf.readLong();
    // 是否可省略?
    byte separator = buf.readByte();
    int idx = buf.readerIndex();
    String msg = buf.slice(idx, buf.readableBytes()).toString(StandardCharsets.UTF_8);
    LogMsg event = new LogMsg(datagramPacket.sender(), msg, msgId);
    list.add(event);
  }
}
