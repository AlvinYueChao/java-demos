package org.example.alvin.algorithm.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class WorldReverseOrder {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String testStr = reader.readLine();
    String[] words = testStr.split(" ");
    Stack<String> wordsStack = new Stack<>();
    for (String word : words) {
      wordsStack.push(word);
    }

    String[] newWords = new String[words.length];
    for (int i = 0; i < words.length; i++) {
      newWords[i] = wordsStack.pop();
    }

    for (String newWord : newWords) {
      System.out.print(newWord + " ");
    }
  }
}
