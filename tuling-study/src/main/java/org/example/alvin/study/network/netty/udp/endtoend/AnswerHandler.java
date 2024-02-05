package org.example.alvin.study.network.netty.udp.endtoend;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author AlvinPower
 * @date 2021/7/11
 */
@Slf4j
public class AnswerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

  private static final String[] ANSWER_DICTIONARY =
      new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
  private static final Random SED = new Random();

  private String getNextQuote() {
    return ANSWER_DICTIONARY[SED.nextInt(ANSWER_DICTIONARY.length)];
  }

  @Override
  protected void channelRead0(
      ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
    String request = datagramPacket.content().toString(StandardCharsets.UTF_8);
    log.info("Received request: {}", request);
    if (StringUtils.equalsIgnoreCase(request, UdpQuestionSide.QUESTION)) {
      String answer = UdpAnswerSide.ANSWER_PREFIX + getNextQuote();
      channelHandlerContext.writeAndFlush(
          new DatagramPacket(
              Unpooled.copiedBuffer(answer, StandardCharsets.UTF_8), datagramPacket.sender()));
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
    log.error("Caught exception when processing the request", cause);
  }
}
