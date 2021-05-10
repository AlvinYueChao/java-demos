package org.example.alvin.study.network.netty.echo;

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

  public static void main(String[] args) {
    int port = 8080;
    String host = "127.0.0.1";
    EchoClient echoClient = new EchoClient(port, host);
    echoClient.start();
  }

  private void start() {
    // TODO
  }
}
