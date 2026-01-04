package org.example.alvin.algorithm.leetcode.greedyalgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LCP502 {

  public static void main(String[] args) {

  }

  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    int n = profits.length;
    // 将 capital 和 profits 绑定在一起，二维数组比用对象效率更高
    // 格式：[capital, profit]
    int[][] projects = new int[n][2];
    for (int i = 0; i < n; i++) {
      projects[i][0] = capital[i];
      projects[i][1] = profits[i];
    }
    // 按照启动资金（capital）进行升序排序
    Arrays.sort(projects, (a, b) -> a[0] - b[0]);
    // 创建一个大顶堆，保存当前能买得起的项目的利润
    PriorityQueue<Integer> maxProfitQueue = new PriorityQueue<>((a, b) -> b - a);
    // 指向排序后的 projects 数组的指针
    int curr = 0;
    // 最多做 k 个项目
    for (int i = 0; i < k; i++) {
      // 遍历 projects 数组，将所有启动资金小于等于 w 的项目添加到 maxProfitQueue 中
      while (curr < n && projects[curr][0] <= w) {
        maxProfitQueue.add(projects[curr][1]);
        curr++;
      }
      // 如果堆为空，说明当前资金不足以启动任何新项目，且没有备选项目，提前结束，直接返回
      if (maxProfitQueue.isEmpty()) {
        break;
      }
      // 从 maxProfitQueue 中取出利润最大的项目，并更新 w
      w += maxProfitQueue.poll();
    }
    return w;
  }
}
