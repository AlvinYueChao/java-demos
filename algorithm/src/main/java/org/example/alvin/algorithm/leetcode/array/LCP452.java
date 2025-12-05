package org.example.alvin.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

public class LCP452 {
  public static void main(String[] args) {
    int[][] points = new int[][] {{-2147483646, -2147483645}, {2147483646, 2147483647}};
    LCP452 lcp452 = new LCP452();
    int result = lcp452.findMinArrowShots(points);
    System.out.println(result);
  }

  public int findMinArrowShots(int[][] points) {
    // 按照右边界升序原则排列所有气球
    // 使用Integer.compare避免整数溢出问题
    Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
    int n = points.length;
    boolean[] shouted = new boolean[n];
    int ans = 0;

    for (int i = 0; i < n; i++) {
      if (shouted[i]) {
        continue;
      }
      ans++;
      for (int j = i; j < n; j++) {
        if (points[j][0] <= points[i][1]) {
          // 有交叉说明两个气球可以由一只箭击破
          shouted[j] = true;
        } else {
          break;
        }
      }
    }
    return ans;
  }

  public int findMinArrowShots2(int[][] points) {
    // 按照右边界升序原则排列所有气球
    Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
    int n = points.length;
    int ans = 1;

    int end = points[0][1];
    for (int i = 1; i < n; i++) {
      if (points[i][0] > end) {
        end = points[i][1];
        ans++;
      }
    }

    return ans;
  }
}
