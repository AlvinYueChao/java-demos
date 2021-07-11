package org.example.alvin.study.network.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AlvinPower
 * @date 2021/7/11
 */
@Slf4j
public class UdpQuestionSide {

  public static final String QUESTION = "今天星期几";

  private static void run(int port) {
    EventLoopGroup group = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group)
        .channel(NioDatagramChannel.class) // UDP通信
        .handler(new QuestionHandler());
    try {
      Channel channel = bootstrap.bind(0).sync().channel();
      channel.writeAndFlush(new DatagramPacket(
          Unpooled.copiedBuffer(QUESTION, StandardCharsets.UTF_8),
          new InetSocketAddress("127.0.0.1", port)
      )).sync();
      if (!channel.closeFuture().await(15000)) {
        log.info("等待超时，直接退出");
      }
    } catch (InterruptedException e) {
      log.warn("发送过程被打断了", e);
      Thread.currentThread().interrupt();
    } finally {
      group.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    run(UdpAnswerSide.ANSWER_PORT);
  }
}
