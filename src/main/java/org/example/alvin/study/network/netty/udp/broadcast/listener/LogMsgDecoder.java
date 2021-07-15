package org.example.alvin.study.network.netty.udp.broadcast.listener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import org.example.alvin.study.network.netty.udp.broadcast.LogMsg;

/**
 * @author AlvinPower
 * @date 2021/7/15
 */
public class LogMsgDecoder extends MessageToMessageDecoder<LogMsg> {

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, LogMsg logMsg, List<Object> list) throws Exception {

  }
}
