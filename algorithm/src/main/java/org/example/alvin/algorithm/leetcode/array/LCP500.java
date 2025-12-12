package org.example.alvin.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/** leetcode 500 */
public class LCP500 {
  public static void main(String[] args) {
    String[] input = {"Hello", "Alaska", "Dad", "Peace"};
    String[] keyBoardWords = {"qwertyuiopQWERTYUIOP", "asdfghjklASDFGHJKL", "zxcvbnmZXCVBNM"};
    List<String> result = new ArrayList<>();

    boolean matched = false;
    for (String word : input) {
      if (keyBoardWords[0].contains(String.valueOf(word.charAt(0)))) {
        matched = word.length() <= 1 || isMatched(word, keyBoardWords[0]);
      } else if (keyBoardWords[1].contains(String.valueOf(word.charAt(0)))) {
        matched = word.length() <= 1 || isMatched(word, keyBoardWords[1]);
      } else {
        matched = word.length() <= 1 || isMatched(word, keyBoardWords[2]);
      }

      if (matched) {
        result.add(word);
      }
    }
    System.out.println(result);
  }

  private static boolean isMatched(String inputWord, String keyBoardWord) {
    boolean matched = false;
    for (int i = 1; i < inputWord.length(); i++) {
      if (keyBoardWord.contains(String.valueOf(inputWord.charAt(i)))) {
        matched = true;
      } else {
        matched = false;
        break;
      }
    }
    return matched;
  }
}
