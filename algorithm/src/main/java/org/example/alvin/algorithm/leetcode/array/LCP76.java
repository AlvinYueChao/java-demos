package org.example.alvin.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class LCP76 {
  public static void main(String[] args) {
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(new LCP76().minWindow(s, t));
  }

  public String minWindow(String s, String t) {
    // 统计需求
    Map<Character, Integer> need = new HashMap<>();
    for (char c : t.toCharArray()) {
      need.put(c, need.getOrDefault(c, 0) + 1);
    }

    // 初始化窗口和变量
    Map<Character, Integer> window = new HashMap<>();
    int left = 0;
    int right = 0;
    // 记录窗口中属于t的不同字符的个数
    int valid = 0;
    // 记录最小覆盖子串的起始索引及长度
    int start = 0;
    int minLen = Integer.MAX_VALUE;

    while (right < s.length()) {
      // 当前字符
      char c = s.charAt(right);
      // 扩大窗口，表示下一次将要读取的字符下标
      right++;

      if (need.containsKey(c)) {
        window.put(c, window.getOrDefault(c, 0) + 1);
        // Integer对象的比较，使用equals
        if (window.get(c).equals(need.get(c))) {
          valid++;
        }
      }

      // 判断窗口是否要收缩
      while (valid == need.size()) {
        // right表示下一次将要读取的字符下标，所以计算minLen时不用+1
        if (right - left < minLen) {
          start = left;
          minLen = right - left;
        }

        char d = s.charAt(left);
        // 移除d之后，window起始位的下标
        left++;

        if (need.containsKey(d)) {
          // 如果移除前窗口中该字符数量刚好满足要求，移除后就不满足了，则valid减一
          if (window.get(d).equals(need.get(d))) {
            valid--;
          }
          window.put(d, window.get(d) - 1);
        }
      }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
  }
}
