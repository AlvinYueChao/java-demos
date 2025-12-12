package org.example.alvin.algorithm.leetcode.array;

import java.util.Arrays;
import org.example.alvin.algorithm.interview.MaxSubSequence;

public class LCR119 {
  public int longestConsecutive(int[] nums) {
    if (nums.length < 2) {
      return nums.length;
    }

    Arrays.sort(nums);

    int maxLength = 1;
    int tmp = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        continue;
      }

      if (nums[i] == nums[i - 1] + 1) {
        tmp += 1;
        maxLength = Math.max(maxLength, tmp);
      } else {
        tmp = 1;
      }
    }

    return maxLength;
  }

  public static void main(String[] args) {
    int[] nums = {3, 1, 5, 4, 2, 7, 9, 8, 11};
    System.out.println(new MaxSubSequence().longestConsecutive(nums));
  }
}
