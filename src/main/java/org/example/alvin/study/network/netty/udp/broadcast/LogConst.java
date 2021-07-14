package org.example.alvin.study.network.netty.udp.broadcast;

import java.util.Random;

/**
 * @author AlvinPower
 * @date 2021/7/14
 */
public class LogConst {

  public static final int MONITOR_SIDE_PORT = 9998;
  private static final String[] LOG_MESSAGES = {
      "2021-01-10 16:18:19 [arthas-binding-thread] INFO  c.t.arthas.core.util.ArthasBanner -Current arthas version: 3.4.5, recommend latest version: 3.4.5",
      "2021-01-10 16:18:19 [arthas-binding-thread] INFO  c.t.arthas.core.util.ArthasBanner -Current arthas version: 3.4.5, recommend latest version: 3.4.5",
      "2021-01-10 16:18:20 [arthas-NettyHttpTelnetBootstrap-3-1] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0x0bfdf01c] REGISTERED",
      "2021-01-10 16:18:20 [arthas-NettyHttpTelnetBootstrap-3-1] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0x0bfdf01c] BIND: /127.0.0.1:3658",
      "2021-01-10 16:18:20 [arthas-NettyHttpTelnetBootstrap-3-1] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0x0bfdf01c, L:/127.0.0.1:3658] ACTIVE",
      "2021-01-10 16:18:20 [arthas-NettyWebsocketTtyBootstrap-4-1] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0xc358759c] REGISTERED",
      "2021-01-10 16:18:20 [arthas-NettyWebsocketTtyBootstrap-4-1] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0xc358759c] BIND: /127.0.0.1:8563",
      "2021-01-10 16:18:20 [arthas-NettyWebsocketTtyBootstrap-4-1] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0xc358759c, L:/127.0.0.1:8563] ACTIVE",
      "2021-01-10 16:18:20 [arthas-NettyWebsocketTtyBootstrap-4-2] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0xb84d802b] REGISTERED",
      "2021-01-10 16:18:20 [arthas-NettyWebsocketTtyBootstrap-4-2] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0xb84d802b] BIND: local:arthas-netty-localaddress",
      "2021-01-10 16:18:20 [arthas-NettyWebsocketTtyBootstrap-4-2] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0xb84d802b, L:local:arthas-netty-localaddress] ACTIVE",
      "2021-01-10 16:18:20 [arthas-binding-thread] INFO  c.t.a.core.server.ArthasBootstrap -as-server listening on network=127.0.0.1;telnet=3658;http=8563;timeout=6000;",
      "2021-01-10 16:18:20 [arthas-binding-thread] INFO  c.t.a.core.server.ArthasBootstrap -as-server started in 2351 ms",
      "2021-01-10 16:18:20 [arthas-NettyHttpTelnetBootstrap-3-1] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0x0bfdf01c, L:/127.0.0.1:3658] READ: [id: 0xe966efdb, L:/127.0.0.1:3658 - R:/127.0.0.1:50305]",
      "2021-01-10 16:18:20 [arthas-NettyHttpTelnetBootstrap-3-1] INFO  c.a.a.d.i.n.h.logging.LoggingHandler -[id: 0x0bfdf01c, L:/127.0.0.1:3658] READ COMPLETE",
      "2021-01-10 16:18:20 [arthas-NettyHttpTelnetBootstrap-3-2] INFO  c.t.a.core.shell.term.impl.Helper -Loaded arthas keymap file from com/taobao/arthas/core/shell/term/readline/inputrc"
  };

  private static final Random SEED = new Random();

  public static String getLogInfo() {
    return LOG_MESSAGES[SEED.nextInt(LOG_MESSAGES.length)];
  }
}
