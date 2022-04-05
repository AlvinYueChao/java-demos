package org.example.alvin.study.network.netty.udp.broadcast;

import java.net.InetSocketAddress;
import lombok.Getter;

/**
 * @author AlvinPower
 * @date 2021/7/14
 */
public final class LogMsg {

  public static final byte SEPARATOR = (byte) ';';

  @Getter private final InetSocketAddress source;
  @Getter private final String msg;
  @Getter private final long msgId;
  @Getter private final long time;

  private LogMsg(InetSocketAddress source, String msg, long msgId, long time) {
    this.source = source;
    this.msg = msg;
    this.msgId = msgId;
    this.time = time;
  }

  public LogMsg(String msg) {
    this(null, msg, -1, System.currentTimeMillis());
  }

  public LogMsg(InetSocketAddress source, String msg, long msgId) {
    this(source, msg, msgId, System.currentTimeMillis());
  }
}
