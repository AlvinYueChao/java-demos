package org.example.alvin.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCP57 {
  public static void main(String[] args) {
    int[][] intervals = new int[][]{{1, 3}, {6, 9}};
    int[] newInterval = new int[]{2, 5};
    LCP57 lcp57 = new LCP57();
    int[][] result = lcp57.insert(intervals, newInterval);
    System.out.println(Arrays.deepToString(result));
  }

  public int[][] insert(int[][] intervals, int[] newInterval) {
    if (intervals.length == 0) {
      return new int[][]{newInterval};
    }
    int left = newInterval[0];
    int right = newInterval[1];
    boolean placed = false;
    List<int[]> ansList = new ArrayList<>();
    for (int[] interval : intervals) {
      if (interval[0] > right) {
        // 在插入区间的右侧且无交集
        if (!placed) {
          ansList.add(new int[]{left, right});
          placed = true;
        }
        ansList.add(interval);
      } else if (interval[1] < left) {
        // 在插入区间的左侧且无交集
        ansList.add(interval);
      } else {
        // 与插入区间有交集，计算它们的并集
        left = Math.min(left, interval[0]);
        right = Math.max(right, interval[1]);
      }
    }
    if (!placed) {
      ansList.add(new int[]{left, right});
    }

    int[][] ans = new int[ansList.size()][2];
    for (int i = 0; i < ansList.size(); ++i) {
      ans[i] = ansList.get(i);
    }
    return ans;
  }
}
