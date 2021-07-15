package org.example.alvin.study.network.netty.udp.broadcast.listener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.alvin.study.network.netty.udp.broadcast.LogMsg;

/**
 * @author AlvinPower
 * @date 2021/7/15
 */
public class LogMsgHandler extends SimpleChannelInboundHandler<LogMsg> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogMsg logMsg) throws Exception {

  }
}
