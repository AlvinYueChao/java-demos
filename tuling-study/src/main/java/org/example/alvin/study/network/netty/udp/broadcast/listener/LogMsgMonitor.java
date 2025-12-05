package org.example.alvin.study.network.netty.udp.broadcast.listener;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;
import org.example.alvin.study.network.netty.udp.broadcast.LogConst;

/**
 * @author AlvinPower
 * @date 2021/7/15
 */
@Slf4j
public class LogMsgMonitor {

  private final EventLoopGroup group;
  private final Bootstrap bootstrap;

  public LogMsgMonitor(InetSocketAddress remoteAddress) {
    this.group = new NioEventLoopGroup();
    this.bootstrap = new Bootstrap();
    this.bootstrap
        .group(this.group)
        .channel(NioDatagramChannel.class)
        .option(ChannelOption.SO_BROADCAST, true)
        .option(ChannelOption.SO_REUSEADDR, true)
        .handler(
            new ChannelInitializer<NioDatagramChannel>() {
              @Override
              protected void initChannel(NioDatagramChannel nioDatagramChannel) {
                ChannelPipeline pipeline = nioDatagramChannel.pipeline();
                pipeline.addLast(new LogMsgDecoder()).addLast(new LogMsgHandler());
              }
            })
        // localAddress的作用:
        .localAddress(remoteAddress);
  }

  public Channel bind() {
    return this.bootstrap.bind().syncUninterruptibly().channel();
  }

  public void stop() {
    this.group.shutdownGracefully();
  }

  public static void main(String[] args) {
    LogMsgMonitor monitor = new LogMsgMonitor(new InetSocketAddress(LogConst.MONITOR_SIDE_PORT));
    Channel channel = monitor.bind();
    log.info("Log message monitor is running...");
    try {
      channel.closeFuture().sync();
    } catch (InterruptedException e) {
      log.error("Caught exception when running log message monitor", e);
      Thread.currentThread().interrupt();
    } finally {
      monitor.stop();
    }
  }
}
