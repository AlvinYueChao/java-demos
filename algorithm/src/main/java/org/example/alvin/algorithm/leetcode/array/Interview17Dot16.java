package org.example.alvin.algorithm.leetcode.array;

/** leetcode 面试题 17.16. 按摩师 */
public class Interview17Dot16 {
  public static void main(String[] args) {
    int[] nums = {2, 1, 4, 5, 3, 1, 1, 3};
    System.out.println(massage2(nums));
  }

  // 空间复杂度为 O(n)
  private static int massage(int[] nums) {
    // dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
    if (nums.length == 0) {
      return 0;
    } else if (nums.length == 1) {
      return nums[0];
    } else {
      int[] dp = new int[nums.length];
      dp[0] = nums[0];
      dp[1] = Math.max(nums[0], nums[1]);
      for (int i = 2; i < nums.length; i++) {
        dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
      }
      return dp[nums.length - 1];
    }
  }

  // 空间复杂度为 O(1)
  private static int massage2(int[] nums) {
    int a = 0, b = 0;
    int c = 0;
    for (int num : nums) {
      c = Math.max(a, b + num);
      b = a;
      a = c;
    }
    return c;
  }
}
