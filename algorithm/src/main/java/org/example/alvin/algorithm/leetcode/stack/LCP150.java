package org.example.alvin.algorithm.leetcode.stack;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayDeque;
import java.util.Deque;

public class LCP150 {
  public static void main(String[] args) {
    String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
    LCP150 lcp150 = new LCP150();
    System.out.println(lcp150.evalRPN(tokens));
  }

  public int evalRPN(String[] tokens) {
    if (tokens.length == 1) {
      return Integer.parseInt(tokens[0]);
    }

    StopWatch stopWatch = new StopWatch();
    Deque<Integer> stack = new ArrayDeque<>();

    stopWatch.start();
    for (int i = 0; i < tokens.length; i++) {
      String curr = tokens[i];
      /* 在算法中尽量避免使用正则表达式判断是否是数字，本题中时间消耗差距40倍
      * 正则表达式用时消耗： 1072800 纳秒
      * 排除法用时消耗： 26200 纳秒
      * */
      boolean isNumber = isNumber(curr);
//      boolean isNumber = isNumberByRegex(curr);
      if (isNumber) {
        int currNum = Integer.parseInt(curr);
        stack.push(currNum);
      } else {
        int secondNum = stack.pop();
        int firstNum = stack.pop();
        int result = calculate(firstNum, secondNum, curr);
        stack.push(result);
      }
    }
    stopWatch.stop();
    System.out.println("排除法用时消耗： " + stopWatch.getNanoTime() + " 纳秒");
//    System.out.println("正则表达式用时消耗： " + stopWatch.getNanoTime() + " 纳秒");
    return stack.pop();
  }

  private boolean isNumber(String token) {
    return !"+".equals(token) && !"-".equals(token) && !"*".equals(token) && !"/".equals(token);
  }

  private boolean isNumberByRegex(String token) {
    return token.matches("-?\\d+");
  }

  private int calculate(int firstNum, int secondNum, String operator) {
    int result = 0;
    switch (operator) {
      case "+" -> result = firstNum + secondNum;
      case "-" -> result = firstNum - secondNum;
      case "*" -> result = firstNum * secondNum;
      case "/" -> result = firstNum / secondNum;
      default -> throw new IllegalArgumentException("参数不合法!");
    }
    return result;
  }
}
