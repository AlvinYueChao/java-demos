package org.example.alvin.algorithm.niuke;

import java.util.Scanner;

/**
 * 小红今天研究到了一个新的函数f(x):该函数表示x的二进制表示中，后缀最大连续1串所代表的十进制数（值）。换句话说，是x的二进制表示中，从最低位开始向更高位连续出现的1的最长串所转换得到的十进制数。例如：
 * 59的二级制为（111011)，后缀最大连续1的长度为2，对应的二进制串为（11），即3，因此f(59) = 3， 同样的有f(5)= 1, f(6)= 0;
 * 现在小红想要计算从1到n这n个数中，所有f(i)的和 输入描述： 每个测试文件均包含多组测试数据，第一行输入一个整数T代表数据组数，每组测试数据描述如下：输入一个整数n代表计算范围的上限
 * 输出描述： 对于每组测试数据，新起一行，输出一个整数，表示所有f(i)的和 示例： 输入： 5 1 2 3 4 5 输出： 1 1 4 4 5
 */
public class XiaohongNewFunc {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int T = in.nextInt();

    for (int i = 0; i < T; i++) {
      long n = in.nextLong();
      System.out.println(calculateSum(n));
    }

    in.close();
  }

  // 使用数学方法优化计算从1到n的所有f(i)的和
  private static long calculateSum(long n) {
    if (n <= 0) return 0;

    long sum = 0;
    // 对于每个可能的连续1的长度k，计算有多少个数的f值包含2^k-1
    for (long k = 1; (1L << k) - 1 <= n; k++) {
      long value = (1L << k) - 1; // 2^k - 1，即k个连续1组成的数值
      // 计算有多少个数i (1 <= i <= n) 满足f(i) = value
      // 这些数的形式是: (任意二进制数) + (k个连续的1) ，且该数不能以1结尾（否则连续的1会更多）
      // 即形式为: m * 2^k + (2^k - 1) 其中 m >= 0
      // 并且 m * 2^k + (2^k - 1) <= n
      // 即 m <= (n - (2^k - 1)) / 2^k

      long maxM = (n - value) >> k; // 等价于 (n - value) / (2^k)
      sum += value * (maxM + 1); // +1是因为m从0开始
    }

    return sum;
  }
}
