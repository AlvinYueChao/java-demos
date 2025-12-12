package org.example.alvin.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCP30 {
  public static void main(String[] args) {
    String s = "wordgoodgoodgoodbestword";
    String[] words = {"word", "good", "best", "good"};
    System.out.println(new LCP30().findSubstring(s, words));
  }

  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> ans = new ArrayList<>();
    // 统计 words 中每个单词出现的次数
    Map<String, Integer> allWords = new HashMap<>();
    for (String word : words) {
      allWords.put(word, allWords.getOrDefault(word, 0) + 1);
    }

    int wordLen = words[0].length();
    int wordNum = words.length;
    int sLen = s.length();

    // 只需遍历 wordLen 次，因为 i=0 和 i=wordLen 在逻辑上是同一个步进序列
    for (int i = 0; i < wordLen; i++) {
      // 定义滑动窗口的左右边界
      int left = i;
      int right = i;
      // 当前窗口内的单词统计
      Map<String, Integer> hasWords = new HashMap<>();
      // 当前窗口内匹配成功的单词数量
      int count = 0;

      // 开始滑动，每次移动一个单词的长度
      while (right + wordLen <= sLen) {
        // 取出右边的一个单词
        String w = s.substring(right, right + wordLen);
        // 窗口右扩
        right += wordLen;

        if (allWords.containsKey(w)) {
          // 如果是目标单词
          hasWords.put(w, hasWords.getOrDefault(w, 0) + 1);
          count++;

          // 如果当前单词数量超过了目标数量，说明窗口不合法，需要左缩
          while (hasWords.get(w) > allWords.get(w)) {
            String leftWord = s.substring(left, left + wordLen);
            hasWords.put(leftWord, hasWords.get(leftWord) - 1);
            count--;
            left += wordLen;
          }

          // 如果匹配数量一致，记录结果
          if (count == wordNum) {
            ans.add(left);
            // 记录后，左边界右移一个单词长度，继续寻找下一个可能的匹配。
            // 注意：这里不用清空 map，而是像普通滑动窗口一样吐出一个单词即可
            String firstWord = s.substring(left, left + wordLen);
            hasWords.put(firstWord, hasWords.get(firstWord) - 1);
            count--;
            left += wordLen;
          }
        } else {
          // 如果已到了非目标单词，整个窗口废弃，重置所有状态，left 直接跳到 right 的位置
          hasWords.clear();
          count = 0;
          left = right;
        }
      }
    }
    return ans;
  }
}
