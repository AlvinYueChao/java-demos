package org.example.alvin.study.multiplethread;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author AlvinPower
 * @date 2021/2/24
 */
@Slf4j
public class ObjectHeaderTest {
  public static void main(String[] args) {

    Object obj_hash = new Object();
    log.info(ClassLayout.parseInstance(obj_hash).toPrintable());

    Object obj_new = new Object();
    log.info(Integer.toHexString(obj_new.hashCode()));
    log.info(ClassLayout.parseInstance(obj_new).toPrintable());

    Object obj_biased = new Object();
    synchronized (obj_biased) {
      log.info(ClassLayout.parseInstance(obj_biased).toPrintable());
    }

    Object obj_light = new Object();
    log.info(Integer.toHexString(obj_light.hashCode()));
    synchronized (obj_light) {
      log.info(ClassLayout.parseInstance(obj_light).toPrintable());
    }

    Object obj_weight = new Object();
    synchronized (obj_weight) {
      log.info(Integer.toHexString(obj_weight.hashCode()));
      log.info(ClassLayout.parseInstance(obj_weight).toPrintable());
    }
  }

  /**
   * 1. 无锁可偏向，但是没有偏向 (无哈希值) 101 java.lang.Object object internals: OFFSET SIZE TYPE DESCRIPTION
   * VALUE 0 4 (object header) 05 00 00 00 (00000101 00000000 00000000 00000000) (5) 4 4 (object
   * header) 00 00 00 00 (00000000 00000000 00000000 00000000) (0) 8 4 (object header) 00 02 00 20
   * (00000000 00000010 00000000 00100000) (536871424) 12 4 (loss due to the next object alignment)
   * Instance size: 16 bytes Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
   *
   * <p>2. 无锁不可偏向 (有哈希值) => 计算了 hashcode,对象头中放不下偏向线程 id 001 7e0e6aa2 java.lang.Object object
   * internals: OFFSET SIZE TYPE DESCRIPTION VALUE 0 4 (object header) 01 a2 6a 0e (00000001
   * 10100010 01101010 00001110) (241869313) 4 4 (object header) 7e 00 00 00 (01111110 00000000
   * 00000000 00000000) (126) 8 4 (object header) 00 02 00 20 (00000000 00000010 00000000 00100000)
   * (536871424) 12 4 (loss due to the next object alignment) Instance size: 16 bytes Space losses:
   * 0 bytes internal + 4 bytes external = 4 bytes total
   *
   * <p>3. 有锁，是偏向锁 101 java.lang.Object object internals: OFFSET SIZE TYPE DESCRIPTION VALUE 0 4
   * (object header) 05 70 14 cf (00000101 01110000 00010100 11001111) (-820744187) 4 4 (object
   * header) 9e 02 00 00 (10011110 00000010 00000000 00000000) (670) 8 4 (object header) 00 02 00 20
   * (00000000 00000010 00000000 00100000) (536871424) 12 4 (loss due to the next object alignment)
   * Instance size: 16 bytes Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
   *
   * <p>4. 有锁，是轻量锁 => 计算 hashcode 仅在 synchronized 代码块外，则该锁是轻量锁 000 18bf3d14 java.lang.Object object
   * internals: OFFSET SIZE TYPE DESCRIPTION VALUE 0 4 (object header) d0 f5 ff 33 (11010000
   * 11110101 11111111 00110011) (872412624) 4 4 (object header) 65 00 00 00 (01100101 00000000
   * 00000000 00000000) (101) 8 4 (object header) 00 02 00 20 (00000000 00000010 00000000 00100000)
   * (536871424) 12 4 (loss due to the next object alignment) Instance size: 16 bytes Space losses:
   * 0 bytes internal + 4 bytes external = 4 bytes total
   *
   * <p>5. 有锁，是重量锁 => 计算 hashcode 在 synchronized 代码块内，则该锁是重量锁 010 4fb64261 java.lang.Object object
   * internals: OFFSET SIZE TYPE DESCRIPTION VALUE 0 4 (object header) 82 9d ee 62 (10000010
   * 10011101 11101110 01100010) (1659805058) 4 4 (object header) 73 01 00 00 (01110011 00000001
   * 00000000 00000000) (371) 8 4 (object header) 00 02 00 20 (00000000 00000010 00000000 00100000)
   * (536871424) 12 4 (loss due to the next object alignment) Instance size: 16 bytes Space losses:
   * 0 bytes internal + 4 bytes external = 4 bytes total
   */
}
