package org.example.alvin.algorithm.leetcode;

/** leetcode 409 */
public class LCP409 {
  public static void main(String[] args) {
    String s = "abbb";
    System.out.println(longestPalindrome(s));
  }

  private static int longestPalindrome(String s) {
    // ASCII: Z = 90; a = 97,数组保存ASCII从'A'到'z'的所有字符出现次数
    int[] allCharacters = new int[58];
    for (char c : s.toCharArray()) {
      int index = c - 'A';
      allCharacters[index]++;
    }

    int length = 0;
    boolean hasOdd = false;
    for (int allCharacter : allCharacters) {
      if (allCharacter % 2 == 0) {
        length += allCharacter;
      } else {
        length += (allCharacter - 1);
        hasOdd = true;
      }
    }
    if (hasOdd) {
      length += 1;
    }

    return length;
  }
}
