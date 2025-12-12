package org.example.alvin.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class LCP46 {
  public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    System.out.println(new LCP46().permute(nums));
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    List<Integer> result = new ArrayList<>();
    boolean[] visited = new boolean[nums.length];

    backtrack(results, result, nums, visited);
    return results;
  }

  private void backtrack(
      List<List<Integer>> results, List<Integer> result, int[] nums, boolean[] visited) {
    if (result.size() == nums.length) {
      results.add(new ArrayList<>(result));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) {
        continue;
      }

      result.add(nums[i]);
      visited[i] = true;
      backtrack(results, result, nums, visited);

      result.removeLast();
      visited[i] = false;
    }
  }
}
