package org.example.alvin.study.jvm;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
  public static void main(String[] args) {
    List<String> names =
        Arrays.asList("Alvin", "Bob", "Ccc", "Ddddd", "Tom", "Ace", "Aaaaaa", "Tina");
    String maxLenStartWithA =
        names.stream()
            .parallel()
            .filter(name -> name.startsWith("A"))
            .mapToInt(String::length)
            .max()
            .toString();
    System.out.println(maxLenStartWithA);
  }
}
