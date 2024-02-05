package org.example.alvin.algorithm.leetcode;

import java.util.Arrays;

/** leetcode 面试题40 */
public class GetLeastNumbers {
  public static void main(String[] args) {
    int[] arr = {10, 3, 2, 1, 0};
    int k = 2;
    System.out.println(Arrays.toString(getLeastNumbers(arr, k)));
  }

  private static int[] getLeastNumbers(int[] arr, int k) {
    Arrays.sort(arr);
    int[] result = new int[k];
    System.arraycopy(arr, 0, result, 0, k);
    return result;
  }
}
