package org.example.alvin.study.jvm;

import lombok.Data;

/**
 * VM 参数： -Xms30m -Xmx30m -XX:MaxMetaspaceSize=30m -XX:+UseConcMarkSweepGC -XX:-UseCompressedOops
 *
 * <p>-Xms{size}: 最小堆大小 -Xmx{size}: 最大堆大小 -XX:MaxMetaspaceSize={size}: 方法区大小,元空间是JDK1.8中方法区的实现
 * -XX:+/-UseConcMarkSweepGC: 启用/禁用老年代的CMS垃圾回收器 -XX:+/-UseCompressedOops: 启用/禁用压缩指针
 *
 * <p>Note: 对于CMS垃圾回收器，对象进入老年代的阈值默认为6.对所有垃圾回收器而言，该阈值最大值为15
 *
 * <p>JHSDB工具，可视化映射JVM的运行信息 启用JHSDB工具：PS D:\Program Files\Java\jdk1.8.0_162\lib> java -cp
 * .\sa-jdi.jar sun.jvm.hotspot.HSDB
 */
public class JVMHSDBTest {
  public static final String MAN_TYPE = "man";
  public static final String WOMAN_TYPE = "woman";

  public static void main(String[] args) {
    Teacher t1 = new Teacher();
    t1.setName("Alvin");
    t1.setAge(26);
    t1.setSexType(MAN_TYPE);

    for (int i = 0; i < 15; i++) {
      System.gc();
    }

    Teacher t2 = new Teacher();
    t2.setName("Alice");
    t2.setAge(20);
    t2.setSexType(WOMAN_TYPE);

    try {
      Thread.sleep(Integer.MAX_VALUE);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Data
  private static class Teacher {
    private String name;
    private String sexType;
    private int age;
  }
}
