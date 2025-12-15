package org.example.alvin.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LCP224 {
  public static void main(String[] args) {

  }

  public int calculate(String s) {
    // 当前层级的计算结果
    int res = 0;
    // 正在读取的数字，可能是多位
    int num = 0;
    // 当前数字的符号，1表示 + ，-1表示 -
    int sign = 1;
    // 存储遇到 ( 之前的 res 和 num
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      char curr = s.charAt(i);
      if (Character.isDigit(curr)) {
        // 拼接数字
        num = num * 10 + (curr - '0');
      } else if (curr == '+' || curr == '-') {
        // 刚才的数字已读完
        res += sign * num;
        // 重置数字，方便继续读取下一个数字
        num = 0;
        // 更新下一个数字的符号, + => pre + num, - => pre + (-1) * num
        sign = curr == '+' ? 1 : -1;
      } else if (curr == '(') {
        // 保存当前的 res 和 sign，去算括号里的东西
        stack.push(res);
        stack.push(sign);
        // 重置 res 和 sign
        res = 0;
        sign = 1;
      } else if (curr == ')') {
        res += sign * num;
        // 括号里的算完了
        if (!stack.isEmpty() && stack.size() >= 2) {
          int lastSign = stack.pop();
          int lastNum = stack.pop();
          // 栈顶的 sign 为二元操作的右操作数的符号，即为当前层级 res 的符号
          res = res * lastSign + lastNum;
          // 重置 num
          num = 0;
        }
      }
    }
    if (num != 0) {
      res += sign * num;
    }
    return res;
  }
}
