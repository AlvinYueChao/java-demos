package org.example.alvin.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hot438 {
  public static void main(String[] args) {
    String s = "cbaebabacd";
    String p = "abc";
    System.out.println(new Hot438().findAnagrams(s, p));
  }

  public List<Integer> findAnagrams(String s, String p) {
    int[] cntP = new int[26];
    for (char c : p.toCharArray()) {
      cntP[c - 'a']++;
    }

    List<Integer> ans = new ArrayList<>();
    int[] cntS = new int[26];
    for (int right = 0; right < s.length(); right++) {
      cntS[s.charAt(right) - 'a']++;
      int left = right - p.length() + 1;
      if (left < 0) {
        continue;
      }
      if (Arrays.equals(cntP, cntS)) {
        ans.add(left);
      }
      cntS[s.charAt(left) - 'a']--;
    }
    return ans;
  }
}
