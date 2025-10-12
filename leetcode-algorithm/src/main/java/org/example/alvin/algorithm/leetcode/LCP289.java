package org.example.alvin.algorithm.leetcode;

import java.util.Arrays;

public class LCP289 {
  public static void main(String[] args) {
    int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
    new LCP289().gameOfLife(board);
    System.out.println(Arrays.deepToString(board));
  }

  public void gameOfLife(int[][] board) {
    int[] neighbors = {0, -1, 1};
    int m = board.length;
    int n = board[0].length;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int liveNeighbors = 0;
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
            // 下标为0处，该细胞本身，不用做计算周围活细胞个数
            if (neighbors[k] != 0 || neighbors[l] != 0) {
              int x = i + neighbors[k];
              int y = j + neighbors[l];
              if (x < 0 || x > m - 1 || y < 0 || y > n - 1) {
                continue;
              }
              // -1 表示该细胞曾经是活细胞，计算当前细胞周围活细胞个数时，也应该考虑该细胞
              if (Math.abs(board[x][y]) == 1) {
                liveNeighbors++;
              }
            }
          }
        }

        // 从活到死，状态为-1，从死到活，状态为2（不影响同步计算的其他细胞）
        if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
          board[i][j] = -1;
        }
        if (board[i][j] == 0 && liveNeighbors == 3) {
          board[i][j] = 2;
        }
      }
    }

    // 将复合临时状态更新为最终状态
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == -1) {
          board[i][j] = 0;
        } else if (board[i][j] == 2) {
          board[i][j] = 1;
        }
      }
    }
  }
}
