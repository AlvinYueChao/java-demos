package org.example.alvin.study.network.netty.udp.endtoend;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AlvinPower
 * @date 2021/7/11
 */
@Slf4j
public class UdpAnswerSide {

  public static final int ANSWER_PORT = 8080;
  public static final String ANSWER_PREFIX = "Today is ";

  private static void run(int port) {
    EventLoopGroup group = new NioEventLoopGroup();
    // udp无连接，没有接收连接的说法，所以即使是服务接收端也应该用 Bootstrap 而不是 ServerBootstrap
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group).channel(NioDatagramChannel.class).handler(new AnswerHandler());
    try {
      ChannelFuture future = bootstrap.bind(port).sync();
      log.info("UDP Answer side is waiting for request...");
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      log.warn("stop waiting for request", e);
      Thread.currentThread().interrupt();
    } finally {
      group.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    run(ANSWER_PORT);
  }
}
