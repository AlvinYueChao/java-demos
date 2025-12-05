package org.example.alvin.algorithm.interview;

public class HighestMountain {

  public int maxLength(int[] nums) {
    int n = nums.length;
    if (n < 3) return 0;

    int maxLen = 0;
    int i = 0;

    while (i < n - 2) {
      // 跳过非递增的部分
      while (i < n - 1 && nums[i] >= nums[i + 1]) {
        i++;
      }

      if (i >= n - 2) break;

      // 计算上升部分的长度
      int up = 0;
      while (i < n - 1 && nums[i] < nums[i + 1]) {
        up++;
        i++;
      }

      // 如果没有上升部分，继续下一轮
      if (up == 0) continue;

      // 计算下降部分的长度
      int down = 0;
      while (i < n - 1 && nums[i] > nums[i + 1]) {
        down++;
        i++;
      }

      // 如果有下降部分，则形成了山脉
      if (down > 0) {
        maxLen = Math.max(maxLen, up + down + 1);
      }

      // 回退一步，因为当前点可能作为下一个山脉的起点
      //      i--;
    }

    return maxLen;
  }

  public static void main(String[] args) {
    //    int[] nums = {2, 5, 2, 1, 5};
    int[] nums = {2, 2, 2, 2, 1};
    System.out.println(new HighestMountain().maxLength(nums));
  }
}
