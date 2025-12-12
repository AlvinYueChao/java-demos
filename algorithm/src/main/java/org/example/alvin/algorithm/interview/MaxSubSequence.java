package org.example.alvin.algorithm.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSubSequence {
  public int longestConsecutive(int[] nums) {
    if (nums.length < 2) {
      return nums.length;
    }

    Arrays.sort(nums);
    List<Integer> result = new ArrayList<>();
    List<Integer> t = new ArrayList<>();

    result.add(nums[0]);
    t.add(nums[0]);

    int maxLength = 1;
    int tmp = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        continue;
      }

      if (nums[i] == nums[i - 1] + 1) {
        tmp += 1;
        maxLength = Math.max(maxLength, tmp);
        t.add(nums[i]);

        if (t.size() > result.size()) {
          result.clear();
          result.addAll(t);
        }
      } else {
        tmp = 1;
        t.clear();
        t.add(nums[i]);
      }
    }

    System.out.println(result);
    return maxLength;
  }

  public static void main(String[] args) {
    //    int[] nums = {3, 1, 5, 4, 2, 7, 9, 8, 11};
    int[] nums = {3, 1, 2, 2, 7, 8, 9, 10};
    System.out.println(new MaxSubSequence().longestConsecutive(nums));
  }
}
