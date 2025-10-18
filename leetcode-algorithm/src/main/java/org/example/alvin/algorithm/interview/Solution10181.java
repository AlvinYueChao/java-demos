package org.example.alvin.algorithm.interview;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/** interview on 2025-10-18-1 */
public class Solution10181 {
  public static void main(String[] args) {
    String res = new Solution10181().removeKNums("1432219", 3);
    System.out.println(res);
  }

  public String removeKNums(String num, int k) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < num.length(); i++) {
      int curr = Integer.parseInt(String.valueOf(num.charAt(i)));
      while (!stack.isEmpty() && k > 0 && stack.peek() > curr) {
        stack.pop();
        k--;
      }
      stack.push(curr);
    }
    while (k > 0) {
      stack.pop();
      k--;
    }
    while (!stack.isEmpty() && stack.peekLast() == 0) {
      stack.removeLast();
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.removeLast());
    }

    return sb.toString();
  }
}
