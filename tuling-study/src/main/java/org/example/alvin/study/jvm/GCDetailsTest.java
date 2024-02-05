package org.example.alvin.study.jvm;

import lombok.*;

/**
 * @author AlvinPower
 * @date 2020/12/30
 *     <p>-XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -XX:+PrintFlagsInitial
 */
public class GCDetailsTest {
  public static void main(String[] args) {
    int count = 1000000;
    for (int i = 0; i < count; i++) {
      new User("user" + i);
    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class User {
    private String name;
  }
}
