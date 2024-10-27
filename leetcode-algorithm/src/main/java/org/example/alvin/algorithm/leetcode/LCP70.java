package org.example.alvin.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LCP70 {
  private static final Map<Integer, Integer> GLOBAL_CALC_HIST = new HashMap<>();

  public static void main(String[] args) {
    GLOBAL_CALC_HIST.clear();
    int result = climbStairs(45);
    System.out.println(result);
  }

  public static int climbStairs(int n) {
    int result;
    if (n == 1) {
      result = 1;
      GLOBAL_CALC_HIST.putIfAbsent(1, 1);
    } else if (n == 2) {
      result = 2;
      GLOBAL_CALC_HIST.putIfAbsent(2, 2);
    } else if (GLOBAL_CALC_HIST.containsKey(n)) {
      result = GLOBAL_CALC_HIST.get(n);
    } else {
      result = climbStairs(n - 1) + climbStairs(n - 2);
      GLOBAL_CALC_HIST.putIfAbsent(n, result);
    }
    return result;
  }
}
