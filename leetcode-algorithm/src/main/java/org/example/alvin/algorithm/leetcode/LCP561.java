package org.example.alvin.algorithm.leetcode;

import java.util.Arrays;

/** leetcode 561 */
public class LCP561 {
  public static void main(String[] args) {
    int[] nums = {1, 4, 3, 2};
    System.out.println(arrayPairSum(nums));
  }

  private static int arrayPairSum(int[] nums) {
    Arrays.sort(nums);

    int sum = 0;
    int index = 0;
    while (index < nums.length) {
      sum += nums[index];
      index += 2;
    }
    return sum;
  }
}
