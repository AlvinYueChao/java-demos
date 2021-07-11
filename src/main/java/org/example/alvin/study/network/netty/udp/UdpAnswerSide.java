package org.example.alvin.study.network.netty.udp;

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
  public static final String ANSWER_PREFIX = "今天星期";

  private static void run(int port) {
    EventLoopGroup group = new NioEventLoopGroup();
    //
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group)
        .channel(NioDatagramChannel.class)
        .handler(new AnswerHandler());
    try {
      ChannelFuture future = bootstrap.bind(port).sync();
      log.info("应答服务已启动...");
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      log.warn("停止接收问答", e);
      Thread.currentThread().interrupt();
    } finally {
      group.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    run(ANSWER_PORT);
  }
}
