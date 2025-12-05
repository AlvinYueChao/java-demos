package org.example.alvin.algorithm.leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class LCP128 {
  public static void main(String[] args) {}

  public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    if (nums.length < 2) {
      return nums.length;
    } else {
      for (int i = 0; i < nums.length; i++) {
        set.add(nums[i]);
      }

      int ans = 0;
      for (int i = 0; i < nums.length; i++) {
        if (set.contains(nums[i] - 1)) {
          continue;
        }
        int next = nums[i] + 1;
        int tmpMaxLength = 1;
        while (set.contains(next)) {
          next++;
          tmpMaxLength++;
        }
        ans = Math.max(ans, tmpMaxLength);
      }
      return ans;
    }
  }
}
