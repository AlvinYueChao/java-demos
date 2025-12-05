package org.example.alvin.algorithm.leetcode.array;

import java.util.*;

public class LCP49 {
  public static void main(String[] args) {
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    List<List<String>> ans = new LCP49().groupAnagrams(strs);
    System.out.println(ans);
  }

  public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> ans = new ArrayList<>();

    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] arr = new char[26];
      for (char c : str.toCharArray()) {
        int index = c - 'a';
        arr[index]++;
      }
      String key = new String(arr);

      map.putIfAbsent(key, new ArrayList<>());
      map.get(key).add(str);
    }

    //    map.forEach((k, v) -> ans.add(v));
    ans.addAll(map.values());

    return ans;
  }
}
