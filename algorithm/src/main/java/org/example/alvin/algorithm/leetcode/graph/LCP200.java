package org.example.alvin.algorithm.leetcode.graph;

public class LCP200 {
  public static void main(String[] args) {
    char[][] grid =
        new char[][] {
          {'1', '1', '1', '1', '0'},
          {'1', '1', '0', '1', '0'},
          {'1', '1', '0', '0', '0'},
          {'0', '0', '0', '0', '0'}
        };
    LCP200 lcp200 = new LCP200();
    System.out.println(lcp200.numIslands(grid));
  }

  public int numIslands(char[][] grid) {
    int rows = grid.length;
    int columns = grid[0].length;
    int islandCount = 0;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        // 发现新陆地
        if (grid[r][c] == '1') {
          islandCount++;
          // 淹没相邻可达陆地
          dfs(grid, r, c, rows, columns);
        }
      }
    }
    return islandCount;
  }

  /**
   * @param grid
   * @param r
   * @param c
   * @param rows
   * @param columns
   */
  private void dfs(char[][] grid, int r, int c, int rows, int columns) {

    if (r < 0 || r >= rows || c < 0 || c >= columns || grid[r][c] == '0') {
      return;
    }

    // grid[r][c] == '1'时，说明是可淹没的相邻陆地
    grid[r][c] = '0';

    // 继续向四面淹没
    dfs(grid, r - 1, c, rows, columns);
    dfs(grid, r + 1, c, rows, columns);
    dfs(grid, r, c - 1, rows, columns);
    dfs(grid, r, c + 1, rows, columns);
  }
}
