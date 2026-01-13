package org.example.alvin.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class Hot3 {
  public static void main(String[] args) {
    String s = "abcabcbb";
    System.out.println(new Hot3().lengthOfLongestSubstring(s));
  }

  public int lengthOfLongestSubstring(String s) {
//    return implOfHash(s);
    return implOfArray(s);
  }

  private int implOfHash(String s) {
    Map<Character, Integer> uniqueChar2CntMap = new HashMap<>();
    int left = 0;
    int right = 0;
    int ans = 0;
    while (right < s.length()) {
      char curr = s.charAt(right);
      uniqueChar2CntMap.put(curr, uniqueChar2CntMap.getOrDefault(curr, 0) + 1);
      int cnt = uniqueChar2CntMap.get(curr);
      while (cnt > 1) {
        char leftChar = s.charAt(left);
        uniqueChar2CntMap.put(leftChar, uniqueChar2CntMap.get(leftChar) - 1);
        left++;
        cnt = uniqueChar2CntMap.get(curr);
      }
      ans = Math.max(ans, right - left + 1);
      right++;
    }
    return ans;
  }

  private int implOfArray(String s) {
    // ASCII 码表总共有128个字符（0-127），但前32个是“控制字符”（如换行符、退格符），它们不是可见的字母或符号
    int[] uniqueChar2CntArray = new int[128];
    int left = 0;
    int right = 0;
    int ans = 0;
    while (right < s.length()) {
      char curr = s.charAt(right);
      uniqueChar2CntArray[curr]++;
      while (uniqueChar2CntArray[curr] > 1) {
        char leftChar = s.charAt(left);
        uniqueChar2CntArray[leftChar]--;
        left++;
      }
      ans = Math.max(ans, right - left + 1);
      right++;
    }
    return ans;
  }
}
