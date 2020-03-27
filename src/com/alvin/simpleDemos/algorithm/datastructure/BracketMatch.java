package com.alvin.simpleDemos.algorithm.datastructure;

public class BracketMatch {
    public static void main(String[] args) {
        String str1 = ")test(";
        String str2 = "(t(e(s)t";
        String str3 = "(t(e)s)t)";
        String str4 = "(t(e(s(t";
        String str5 = "t)e)s)t";
        String str6 = "(t(e)s)t";

        System.out.println(str1 + " 匹配结果: " + bracketMatch(str1));
        System.out.println(str2 + " 匹配结果: " + bracketMatch(str2));
        System.out.println(str3 + " 匹配结果: " + bracketMatch(str3));
        System.out.println(str4 + " 匹配结果: " + bracketMatch(str4));
        System.out.println(str5 + " 匹配结果: " + bracketMatch(str5));
        System.out.println(str6 + " 匹配结果: " + bracketMatch(str6));
    }

    private static boolean bracketMatch(String str) {
        int leftBracketCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                leftBracketCount++;
            }
            else if (str.charAt(i) == ')') {
                leftBracketCount--;
            }
            // 如果左括弧先出现，也视为不匹配
            if (leftBracketCount < 0) {
                break;
            }
        }
        return leftBracketCount == 0;
    }
}
