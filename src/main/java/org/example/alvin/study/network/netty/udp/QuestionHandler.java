package org.example.alvin.study.network.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.net.DatagramPacket;

/**
 * @author AlvinPower
 * @date 2021/7/11
 */
public class QuestionHandler extends SimpleChannelInboundHandler<DatagramPacket> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      DatagramPacket datagramPacket) throws Exception {

  }
}
