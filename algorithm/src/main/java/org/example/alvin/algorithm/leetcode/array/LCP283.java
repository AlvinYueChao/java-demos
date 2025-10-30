package org.example.alvin.algorithm.leetcode.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class LCP283 {
  public static void main(String[] args) {
//    int[] nums = {0, 1, 0, 3, 12};
//    int[] nums = {0};
    int[] nums = {1};
    moveZeroes(nums);
    log.info("result: {}", Arrays.toString(nums));
  }

  public static void moveZeroes(int[] nums) {
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        nums[j++] = nums[i];
      }
    }
    for (int i = j; i < nums.length; i++) {
      nums[i] = 0;
    }
  }
}
