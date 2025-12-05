package org.example.alvin.algorithm.leetcode;

import java.util.Arrays;

public class Solution0927 {
  public static void main(String[] args) {
    System.out.println("dog".hashCode());
    System.out.println("cat".hashCode());
  }

  private static void test2() {
    int[] sca = new int[128];
    System.out.println((int) 'e');
    sca['e'] = 1;
    System.out.println(Arrays.toString(sca));
  }

  private static void test1() {
    System.out.println((int) 'e');
  }
}
