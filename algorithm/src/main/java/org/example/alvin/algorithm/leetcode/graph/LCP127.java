package org.example.alvin.algorithm.leetcode.graph;

import java.util.*;

public class LCP127 {
  public static void main(String[] args) {
    String beginWord = "hit";
    String endWord = "cog";
    List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
    System.out.println(new LCP127().ladderLength(beginWord, endWord, wordList));
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // 第一步：预处理 (优化查找效率)
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) {
      return 0;
    }

    // 第二步：BFS 队列初始化
    Set<String> visited = new HashSet<>();
    Queue<String> wordQueue = new LinkedList<>();
    Queue<Integer> levelQueue = new LinkedList<>();
    visited.add(beginWord);
    wordQueue.offer(beginWord);
    levelQueue.offer(1);
    // 第三步：核心扩散逻辑 (寻找邻居节点)
    while (!wordQueue.isEmpty() && !levelQueue.isEmpty()) {
      String word = wordQueue.poll();
      int level = levelQueue.poll();
      for (int i = 0; i < beginWord.length(); i++) {
        for (int j = 0; j < 26; j++) {
          StringBuilder sb = new StringBuilder(word);
          char c = (char) ('a' + j);
          sb.setCharAt(i, c);
          String newWord = sb.toString();
          if (newWord.equals(endWord)) {
            return level + 1;
          } else if (wordSet.contains(newWord) && !visited.contains(newWord)) {
            visited.add(newWord);
            wordQueue.offer(newWord);
            levelQueue.offer(level + 1);
          }
        }
      }
    }
    return 0;
  }
}
