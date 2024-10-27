package org.example.alvin.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LCP1 {
  public static void main(String[] args) {
    LCP1 lcp1 = new LCP1();
    int[] nums = new int[] {3, 3};
    int target = 6;
    int[] result = lcp1.twoSum(nums, target);
    System.out.println(result[0] + "," + result[1]);
  }

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> numIdxes = new HashMap<>();
    int[] result = new int[2];
    for (int i = 0; i <= nums.length; i++) {
      int gap = target - nums[i];
      if (numIdxes.containsKey(gap)) {
        result[0] = i;
        result[1] = numIdxes.get(gap);
        break;
      } else {
        numIdxes.put(nums[i], i);
      }
    }
    return result;
  }
}
