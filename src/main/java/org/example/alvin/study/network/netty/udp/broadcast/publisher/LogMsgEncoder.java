package org.example.alvin.study.network.netty.udp.broadcast.publisher;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.example.alvin.study.network.netty.udp.broadcast.LogConst;
import org.example.alvin.study.network.netty.udp.broadcast.LogMsg;

/**
 * @author AlvinPower
 * @date 2021/7/14
 */
public class LogMsgEncoder extends MessageToMessageEncoder<LogMsg> {

  private final InetSocketAddress remoteAddress;

  public LogMsgEncoder(InetSocketAddress remoteAddress) {
    this.remoteAddress = remoteAddress;
  }

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, LogMsg logMsg, List<Object> list) throws Exception {
    byte[] bytes = logMsg.getMsg().getBytes(StandardCharsets.UTF_8);
    ByteBuf buf = channelHandlerContext.alloc().buffer(8 * 2 + bytes.length + 1);
    buf.writeLong(logMsg.getTime());
    buf.writeLong(logMsg.getMsgId());
    buf.writeByte(LogConst.MONITOR_SIDE_PORT);
    buf.writeBytes(bytes);
    list.add(new DatagramPacket(buf, this.remoteAddress));
  }
}
