package org.example.alvin.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class LCP77 {
  public static void main(String[] args) {
    int n = 4;
    int k = 2;
    LCP77 lcp77 = new LCP77();
    List<List<Integer>> ans = lcp77.combine(n, k);
    System.out.println(ans);
  }

  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> combinations = new ArrayList<>();
    List<Integer> combination = new ArrayList<>();
    dfs(combinations, combination, 1, n, k);
    return combinations;
  }

  private void dfs(
      List<List<Integer>> combinations, List<Integer> combination, int startIndex, int n, int k) {
    if (combination.size() + n - startIndex + 1 < k) {
      return;
    }
    if (combination.size() == k) {
      combinations.add(new ArrayList<>(combination));
      return;
    }
    combination.add(startIndex);
    dfs(combinations, combination, startIndex + 1, n, k);
    combination.removeLast();
    dfs(combinations, combination, startIndex + 1, n, k);
  }
}
