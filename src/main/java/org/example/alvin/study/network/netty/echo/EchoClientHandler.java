package org.example.alvin.study.network.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AlvinPower
 * @date 2021/5/10
 */
@Slf4j
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

  /**
   * 读取到网络数据之后，进行业务处理
   * Handler 的实现，采用了责任链模式的思想。只需要考虑业务实现，无需考虑资源释放问题
   * @param channelHandlerContext
   * @param byteBuf
   */
  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
    log.info("Client received: {}", byteBuf.toString(StandardCharsets.UTF_8));
  }

  /**
   * channel连接成功后，做业务处理
   * @param ctx
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Netty", CharsetUtil.UTF_8));
  }
}
