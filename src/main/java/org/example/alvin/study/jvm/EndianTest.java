package org.example.alvin.study.jvm;

import java.nio.ByteOrder;
import jdk.internal.misc.Unsafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EndianTest {

  /**
   * JVM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
   * @param args
   */
  public static void main(String[] args) {
    Unsafe unsafe = Unsafe.getUnsafe();
    long a = unsafe.allocateMemory(8);
    try {
      unsafe.putLong(a, 0x0102030405060708L);
      byte b = unsafe.getByte(a);
      ByteOrder byteOrder;
      switch (b) {
        case 0x01:
          byteOrder = ByteOrder.BIG_ENDIAN;
          break;
        case 0x08:
          byteOrder = ByteOrder.LITTLE_ENDIAN;
          break;
        default:
          byteOrder = null;
      }
      log.info("{}", byteOrder);
    } finally {
      unsafe.freeMemory(a);
    }
  }
}
