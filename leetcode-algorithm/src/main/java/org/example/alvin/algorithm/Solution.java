package org.example.alvin.algorithm;

import java.util.Arrays;

public class Solution {
  public static void main(String[] args) {
    int[][] arr = new int[3][3];
    System.out.println(Arrays.deepToString(arr));
  }

  private static void charCalcTest() {
    int a1 = '2' - '0' - 1;
    int a2 = (int) '2' - '1';
    System.out.println(a1 == a2);
  }

  private static void logicalBitMovingTest() {
    int a = 10 >>> 1;
    System.out.println(a);
  }
}
