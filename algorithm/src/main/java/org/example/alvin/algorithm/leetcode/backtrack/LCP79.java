package org.example.alvin.algorithm.leetcode.backtrack;

import java.util.*;

public class LCP79 {
  public static void main(String[] args) {
    char[][] board =
        new char[][] {
          {'A', 'B', 'C', 'E'},
          {'S', 'F', 'C', 'S'},
          {'A', 'D', 'E', 'E'}
        };
    String word = "SEE";
    System.out.println(new LCP79().exist(board, word));
  }

  public boolean exist(char[][] board, String word) {
    List<List<List<Integer>>> paths = new ArrayList<>();
    List<List<Integer>> path = new ArrayList<>();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        backtrack(paths, path, board, i, j, word, 0, new boolean[board.length][board[0].length]);
      }
    }

    System.out.println(paths);
    return !paths.isEmpty();
  }

  private void backtrack(
      List<List<List<Integer>>> paths,
      List<List<Integer>> path,
      char[][] board,
      int r,
      int c,
      String word,
      int index,
      boolean[][] used) {
    if (r < 0
        || r >= board.length
        || c < 0
        || c >= board[0].length
        || board[r][c] != word.charAt(index)
        || used[r][c]) {
      return;
    }

    path.add(List.of(r, c));
    used[r][c] = true;

    if (index == word.length() - 1) {
      paths.add(new ArrayList<>(path));
      return;
    }

    backtrack(paths, path, board, r - 1, c, word, index + 1, used);
    backtrack(paths, path, board, r + 1, c, word, index + 1, used);
    backtrack(paths, path, board, r, c - 1, word, index + 1, used);
    backtrack(paths, path, board, r, c + 1, word, index + 1, used);

    path.removeLast();
    used[r][c] = false;
  }
}
