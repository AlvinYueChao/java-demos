package org.example.alvin.study.network.netty.udp.broadcast.publisher;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;
import org.example.alvin.study.network.netty.udp.broadcast.LogConst;
import org.example.alvin.study.network.netty.udp.broadcast.LogMsg;

/**
 * @author AlvinPower
 * @date 2021/7/14
 */
@Slf4j
public class LogMsgBroadcaster {

  private final EventLoopGroup group;
  private final Bootstrap bootstrap;

  public LogMsgBroadcaster(InetSocketAddress remoteAddress) {
    this.group = new NioEventLoopGroup();
    this.bootstrap = new Bootstrap();
    this.bootstrap.group(this.group).channel(NioDatagramChannel.class)
        // 设置广播模式
        .option(ChannelOption.SO_BROADCAST, true)
        .handler(new LogMsgEncoder(remoteAddress));
  }

  private void run() throws InterruptedException {
    Channel channel = this.bootstrap.bind(0).sync().channel();
    long count = 0;
    for (; ; ) {
      log.info("Start publishing log message in broadcast mode...");
      channel.writeAndFlush(new LogMsg(null, LogConst.getLogInfo(), ++count));
      // 为什么要用中断来结束广播日志信息？
      // 如何中断？
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  public void close() {
    this.group.shutdownGracefully();
  }

  public static void main(String[] args) {
    // 在相关协议中广播固定地址：255.255.255.255
    LogMsgBroadcaster broadcaster = new LogMsgBroadcaster(new InetSocketAddress("255.255.255.255", LogConst.MONITOR_SIDE_PORT));
    try {
      broadcaster.run();
    } catch (InterruptedException e) {
      log.warn("Caught exception when broadcasting log message", e);
      Thread.currentThread().interrupt();
    } finally {
      broadcaster.close();
    }
  }
}
