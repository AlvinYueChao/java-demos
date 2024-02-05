package org.example.alvin.study.network.netty.http.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * @author AlvinPower
 * @date 2021/6/17
 */
public class ServerHandlerInit extends ChannelInitializer<SocketChannel> {

  private SslContext sslContext;
  private static final int MAX_CONTENT_LENGTH = 10 * 1024 * 1024;

  public ServerHandlerInit(SslContext sslContext) {
    this.sslContext = sslContext;
  }

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline ph = socketChannel.pipeline();
    // 服务器是解码httpRequest，编码httpResponse;客户端反过来
    ph.addLast("decoder", new HttpRequestDecoder());
    ph.addLast("encoder", new HttpResponseEncoder());
    // 聚合http报文，防止自动切片
    ph.addLast("aggregator", new HttpObjectAggregator(MAX_CONTENT_LENGTH));
    // 压缩http报文，可选
    ph.addLast("compressor", new HttpContentCompressor());
    ph.addLast(new BusinessHandler());
  }
}
