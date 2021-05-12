package org.example.alvin.study.network.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AlvinPower
 * @date 2021/5/10
 */
@Slf4j
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ByteBuf inputBuffer = (ByteBuf) msg;
    log.info("Server received: {}", inputBuffer.toString(CharsetUtil.UTF_8));
    ctx.writeAndFlush(inputBuffer);
    ctx.close();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    log.error("Caught exception in server side", cause);
    ctx.close();
  }
}
