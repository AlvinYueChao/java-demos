package org.example.alvin.study.network.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author AlvinPower
 * @date 2021/7/11
 */
@Slf4j
public class QuestionHandler extends SimpleChannelInboundHandler<DatagramPacket> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
    String response = datagramPacket.content().toString(StandardCharsets.UTF_8);
    if (StringUtils.startsWith(response, UdpAnswerSide.ANSWER_PREFIX)) {
      log.info("Received response: {}", response);
      channelHandlerContext.close();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
    log.error("Caught exception when processing the request", cause);
  }
}
