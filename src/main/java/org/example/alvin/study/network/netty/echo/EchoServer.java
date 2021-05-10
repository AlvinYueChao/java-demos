package org.example.alvin.study.network.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AlvinPower
 * @date 2021/5/10
 */
@Slf4j
public class EchoServer {

  private final int port;

  public EchoServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) throws InterruptedException {
    int port = 8080;
    EchoServer echoServer = new EchoServer(port);
    log.info("服务器即将启动");
    echoServer.start();
    log.info("服务器关闭");
  }

  private void start() throws InterruptedException {
    // 线程组
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      // 服务器启动必备
      ServerBootstrap bootstrap = new ServerBootstrap();
      final EchoServerHandler echoServerHandler = new EchoServerHandler();
      bootstrap
          .group(group) // 指定工作线程组
          .channel(NioServerSocketChannel.class)  // 指定通信模式采用NIO服务端模式
          .localAddress(new InetSocketAddress(this.port))  // 指定监听端口
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
              socketChannel.pipeline().addLast(echoServerHandler);
            }
          });
      // 服务器异步绑定到指定端口. sync() 会阻塞到绑定工作完全做完
      ChannelFuture bindingResult = bootstrap.bind().sync();
      // 阻塞当前线程，直到服务器的 Channel 被关闭
      bindingResult.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully().sync();
    }
  }
}
