package org.example.alvin.study.jvm;

import java.nio.ByteOrder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EndianTest {

  public static void main(String[] args) {
    var unsafe = UnsafeFactory.getUnsafeInstance();
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
