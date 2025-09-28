package org.example.alvin.algorithm.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目1：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了不止一次。找出那个只出现了一次的元素。 示例 : 输入: [2,2,3,2,4,5,4,5,5] 输出: 3
 * class Solution { public int singleNumber(int[] nums) { } } 题目2: 公司数据库中有“项目表”和“部门表”： 部门表
 * department (编号id, 部门名称name) 项目表 project (编号id, 项目名name, 归属部门dept_id) 写一句SQL: 统计各部门的项目数量 输出示例:
 * 部门名字 项目数量 销售部 3
 */
class Solution {
  public int singleNumber(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    int ans = 0;
    for (int i = 0; i < nums.length; i++) {
      int count = map.getOrDefault(nums[i], 0) + 1;
      map.put(nums[i], count);
    }
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        ans = entry.getKey();
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {2, 2, 3, 2, 4, 5, 4, 5, 5};
    int ans = new Solution().singleNumber(nums);
    System.out.println(ans);
  }

  private static void log2() {
    double res = Math.log(11) / Math.log(2);
    System.out.println(res);
  }

  private static void extendedIntMaxValue() {
    int x = 2147483647;
    int y = x * x;
    System.out.println(y);
  }
}
