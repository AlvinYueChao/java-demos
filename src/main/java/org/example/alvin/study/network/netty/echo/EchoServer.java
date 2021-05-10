package org.example.alvin.study.network.netty.echo;

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

  public static void main(String[] args) {
    int port = 8080;
    EchoServer echoServer = new EchoServer(port);
    log.info("服务器即将启动");
    echoServer.start();
    log.info("服务器关闭");
  }

  private void start() {
    // TODO
  }
}
