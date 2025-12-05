package org.example.alvin.algorithm.leetcode.dynamicplan;

import java.util.ArrayList;
import java.util.List;

public class LCP64 {
  public static void main(String[] args) {
    LCP64 lcp64 = new LCP64();
    System.out.println(lcp64.minPathSum(new int[][] {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
  }

  public int minPathSum(int[][] grid) {
    List<List<int[]>> paths = new ArrayList<>();
    List<int[]> path = new ArrayList<>();
    List<Integer> lengths = new ArrayList<>();
    backtrack(paths, path, 0, 0, grid.length, grid[0].length);

    int ans = Integer.MAX_VALUE;
    for (List<int[]> ints : paths) {
      int total = 0;
      for (int[] currPoint : ints) {
        total += grid[currPoint[0]][currPoint[1]];
      }
      ans = Math.min(total, ans);
    }
    return ans;
  }

  private void backtrack(List<List<int[]>> paths, List<int[]> path, int r, int c, int m, int n) {
    if (r >= m || c >= n) {
      return;
    }
    path.add(new int[] {r, c});
    if (r == m - 1 && c == n - 1) {
      paths.add(new ArrayList<>(path));
    } else {
      backtrack(paths, path, r, c + 1, m, n);
      backtrack(paths, path, r + 1, c, m, n);
    }

    // 回退
    path.removeLast();
  }
}
