package org.example.alvin.algorithm.leetcode.array;

public class Classic80 {
  public int removeDuplicates(int[] nums) {
    int maxOccurrence = 1;
    if (nums.length <= maxOccurrence) {
      return nums.length;
    }

    int slow = maxOccurrence;
    int fast = maxOccurrence;
    while (fast < nums.length) {
      if (nums[slow - maxOccurrence] != nums[fast]) {
        nums[slow] = nums[fast];
        slow++;
      }
      fast++;
    }
    return slow;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {0, 0, 1, 1, 1, 1, 2, 3, 3};
    int length = new Classic80().removeDuplicates(nums);
    for (int i = 0; i < length; i++) {
      System.out.println(nums[i]);
    }
  }
}
