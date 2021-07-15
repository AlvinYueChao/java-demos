package org.example.alvin.study.network.netty.udp.broadcast.listener;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;

/**
 * @author AlvinPower
 * @date 2021/7/15
 */
public class LogMsgMonitor {

  private final EventLoopGroup group;
  private final Bootstrap bootstrap;

  public LogMsgMonitor(InetSocketAddress remoteAddress) {
    this.group = new NioEventLoopGroup();
    this.bootstrap = new Bootstrap();
    this.bootstrap.group(this.group).channel(NioDatagramChannel.class)
        .option(ChannelOption.SO_BROADCAST, true)
        .option(ChannelOption.SO_REUSEADDR, true)
        .handler(new ChannelInitializer<NioDatagramChannel>() {
          @Override
          protected void initChannel(NioDatagramChannel nioDatagramChannel){
            ChannelPipeline pipeline = nioDatagramChannel.pipeline();
            pipeline.addLast(new LogMsgDecoder()).addLast(new LogMsgHandler());
          }
        })
        // localAddress的作用:
        .localAddress(remoteAddress);
  }
}
