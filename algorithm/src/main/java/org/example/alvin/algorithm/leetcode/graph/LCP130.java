package org.example.alvin.algorithm.leetcode.graph;

public class LCP130 {
  public static void main(String[] args) {
    char[][] board = new char[][]{
      {'X', 'X', 'X', 'X'},
      {'X', 'O', 'O', 'X'},
      {'X', 'X', 'O', 'X'},
      {'X', 'O', 'X', 'X'}
    };
    LCP130 lcp130 = new LCP130();
    lcp130.solve(board);
  }

  public void solve(char[][] board) {
    int rows = board.length;
    int columns = board[0].length;
    for (int r = 0; r < rows; r++) {
      if (board[r][0] == 'O') {
        dfs(board, r, 0, rows, columns);
      }
      if (board[r][columns - 1] == 'O') {
        dfs(board, r, columns - 1, rows, columns);
      }
    }
    for (int c = 1; c < columns - 1; c++) {
      if (board[0][c] == 'O') {
        dfs(board, 0, c, rows, columns);
      }
      if (board[rows - 1][c] == 'O') {
        dfs(board, rows - 1, c, rows, columns);
      }
    }

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (board[r][c] == 'A') {
          board[r][c] = 'O';
        } else if (board[r][c] == 'O') {
          board[r][c] = 'X';
        }
      }
    }

    System.out.println(board);
  }

  private void dfs(char[][] board, int r, int c, int rows, int columns) {
    if (r < 0 || r >= rows || c < 0 || c >= columns || board[r][c] != 'O') {
      return;
    }
    board[r][c] = 'A';

    dfs(board, r - 1, c, rows, columns);
    dfs(board, r + 1, c, rows, columns);
    dfs(board, r, c - 1, rows, columns);
    dfs(board, r, c + 1, rows, columns);
  }
}
