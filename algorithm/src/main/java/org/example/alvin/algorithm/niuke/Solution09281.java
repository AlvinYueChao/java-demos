package org.example.alvin.algorithm.niuke;

import java.util.*;

public class Solution09281 {
  /**
   * 一线运维人员在对通话流量进行监控，每一段时间内都会出现流量的高峰，流量有高有低形成一个个波峰波谷，运维人员想找到流量变化最快的波峰，你可以帮他吗？
   * 给定一个整数数组nums，代表采样点的流量值，请找到满足以下条件的三元组（i,j,k):其中i<j<k，nums[j] >
   * nums[i]且nums[j]>nums[k]（即j是峰顶）,并找到所有满足条件的三元组中(k-i)的最小值 输入描述：第一行为n个整数，表示数组中的n个元素,0<=n<=100000
   * 输出描述：返回所有满足条件的三元组中(k-i)的最小值。若不存在，返回-1 示例1： 输入： 3 5 4 7 2 1 输出： 2 说明： 满足条件的三元组为：[0,1,2]，距离2 示例2：
   * 输入： 4 3 2 1 输出： -1 说明： 无法找到满足条件的三元组，返回-1
   *
   * @param args
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    List<Integer> list = new ArrayList<>();
    while (in.hasNextInt()) {
      list.add(in.nextInt());
    }
    in.close();
    int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
    System.out.println(findMin(nums));
  }

  public static int findMin(int[] nums) {
    if (nums == null || nums.length < 3) {
      return -1;
    }

    int n = nums.length;
    int[] leftSmaller = new int[n];

    // -1 表示当前元素左侧没有较小元素
    Arrays.fill(leftSmaller, -1);

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
        stack.pop();
      }
      leftSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(i);
    }

    // 清空栈，用于计算右侧最近较小元素
    stack.clear();
    int minDistance = Integer.MAX_VALUE;

    // 计算右侧最近较小元素并同时计算结果
    int[] rightSmaller = new int[n];
    Arrays.fill(rightSmaller, -1);

    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
        stack.pop();
      }
      rightSmaller[i] = stack.isEmpty() ? -1 : stack.peek();

      // 如果当前点可以作为波峰，更新最小距离
      if (leftSmaller[i] != -1 && rightSmaller[i] != -1) {
        minDistance = Math.min(minDistance, rightSmaller[i] - leftSmaller[i]);
      }

      stack.push(i);
    }
    return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
  }
}
