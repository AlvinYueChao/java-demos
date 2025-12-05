package org.example.alvin.study.network.netty.udp.broadcast.listener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.alvin.study.network.netty.udp.broadcast.LogMsg;

/**
 * @author AlvinPower
 * @date 2021/7/15
 */
@Slf4j
public class LogMsgHandler extends SimpleChannelInboundHandler<LogMsg> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogMsg logMsg)
      throws Exception {
    StringBuilder builder = new StringBuilder();
    builder.append(logMsg.getTime());
    builder.append(" [");
    builder.append(logMsg.getSource().toString());
    builder.append("] : [");
    builder.append(logMsg.getMsgId());
    builder.append("] : ");
    builder.append(logMsg.getMsg());
    log.info("Read the log message form the monitor {}", builder.toString());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
    log.error("Caught exception when process the logMsg", cause);
  }
}
