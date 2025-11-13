package org.example.alvin.algorithm.leetcode.graph;

import java.util.Map;

public class LCP211 {
  public static void main(String[] args) {
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    wordDictionary.addWord("mad");
    System.out.println(wordDictionary.search("pad"));
    System.out.println(wordDictionary.search("bad"));
    System.out.println(wordDictionary.search(".ad"));
    System.out.println(wordDictionary.search("b.."));
  }

  static class WordDictionary {
    WordDictionary[] children;
    boolean isEnd;

    public WordDictionary() {
      this.children = new WordDictionary[26];
      this.isEnd = false;
    }

    public void addWord(String word) {
      WordDictionary curr = this;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        int index = c - 'a';
        if (curr.children[index] == null) {
          curr.children[index] = new WordDictionary();
        }
        curr = curr.children[index];
      }
      curr.isEnd = true;
    }

    public boolean search(String word) {
      return searchHelper(word, 0, this);
    }

    private boolean searchHelper(String word, int index, WordDictionary node) {
      // 1. 基本情况（终止条件）：
      // 如果已经匹配到单词的末尾
      if (index == word.length()) {
        // 只有当这个节点
        // 1) 存在 (node != null)
        // 2) 并且它是一个单词的结尾 (node.isEnd)
        // 才算匹配成功

        // （我们可以在调用前检查 null，这里可以简化）
        return node.isEnd;
      }

      char c = word.charAt(index);

      if (c == '.') {
        // 2. 递归情况：处理通配符 '.'
        // 遍历当前节点的所有 26 个子节点
        for (int i = 0; i < 26; i++) {
          WordDictionary child = node.children[i];
          // 如果子节点存在，并且从这个子节点出发能匹配单词的剩余部分
          if (child != null && searchHelper(word, index + 1, child)) {
            // 只要有一条路径能通，就返回 true
            return true;
          }
        }
        // 尝试了所有 26 个子节点，都没有匹配成功
        return false;

      } else {
        // 3. 递归情况：处理普通字母
        int charIndex = c - 'a';
        WordDictionary child = node.children[charIndex];

        // 如果对应的子节点不存在，说明路径断了，匹配失败
        if (child == null) {
          return false;
        }

        // 如果子节点存在，则继续沿着这条路径递归匹配下一个字符
        return searchHelper(word, index + 1, child);
      }
    }
  }
}
