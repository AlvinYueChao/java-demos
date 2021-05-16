package org.example.alvin.study.network.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;

/**
 * @author AlvinPower
 * @date 2021/5/10
 */
public class EchoClient {

  private final int port;
  private final String host;

  public EchoClient(int port, String host) {
    this.port = port;
    this.host = host;
  }

  public static void main(String[] args) throws InterruptedException {
    int port = 8080;
    String host = "127.0.0.1";
    EchoClient echoClient = new EchoClient(port, host);
    echoClient.start();
  }

  private void start() throws InterruptedException {
    // 线程组
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      // 服务器启动必备
      Bootstrap bootstrap = new Bootstrap();
      bootstrap
          .group(group) // 指定工作线程组
          .channel(NioSocketChannel.class)  // 指定通信模式采用NIO客户端模式
          .remoteAddress(new InetSocketAddress(this.host, this.port))  // 指定服务器的ip地址和端口
          .handler(
              new ChannelInitializer<SocketChannel>() {  // 客户端作为请求发起者，所以使用handler而非childHandler
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                  socketChannel.pipeline().addLast(new EchoClientHandler());
                }
              });
      // 异步连接到服务器. sync() 会阻塞到绑定工作完全做完
      ChannelFuture connectedResult = bootstrap.connect().sync();
      // 阻塞当前线程，直到服务器的 Channel 被关闭
      connectedResult.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully().sync();
    }
  }
}
