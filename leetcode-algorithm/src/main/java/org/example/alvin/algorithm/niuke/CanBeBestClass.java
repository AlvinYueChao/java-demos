package org.example.alvin.algorithm.niuke;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 牛牛是一所学校的班主任，这一学期一共有n门期末考试，课程编号依次为1,2,…,n。其中第i门课的班级平均成绩（取整后）为Si，只有满足下述三个条件之一，牛牛的班级才可被称之为优秀班级：
 * 条件一：该n门课平均成绩的中位数不低于a； 条件二：该n门课平均成绩的算术平均值不低于b 条件三：该n门课平均成绩的去极值算术平均不低于c 请你判断牛牛的班级成绩能否被评为优秀班级。 输入描述：
 * 每个测试文件均包含多组测试数据，第一行输入一个整数T（1≤T≤1000），表示数据组数。每组测试数据描述如下： 第一行输入一个整数n（3<=n<=100),表示期末考试科目数量；
 * 第二行输入n个整数s1, s2,...,sn(0<=si<=100)，表示每门课程的班级平均成绩。 第三行输入三个整数a, b, c(1<=a, b,
 * c<=100)，分别代表优秀班级评判条件中的分数要求 输出描述： 对于每组测试数据，新起一行，若班级能被评为优秀班级，则输出Yes，否则输出No。 示例1 输入： 3 3 66 66 66 99
 * 70 60 4 99 10 60 25 43 49 43 6 46 64 0 100 60 88 62 88 77 输出： Yes No Yes
 */
public class CanBeBestClass {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      int n = in.nextInt();
      int[] avgScores = new int[n];
      for (int j = 0; j < n; j++) {
        avgScores[j] = in.nextInt();
      }
      int a = in.nextInt();
      int b = in.nextInt();
      int c = in.nextInt();
      System.out.println(canBeBestClass(avgScores, a, b, c) ? "Yes" : "No");
    }
  }

  private static boolean canBeBestClass(int[] avgScores, int a, int b, int c) {
    int n = avgScores.length;
    int[] sortedScores = new int[n];
    System.arraycopy(avgScores, 0, sortedScores, 0, n);
    Arrays.sort(sortedScores);
    int min = sortedScores[0];
    int max = sortedScores[n - 1];
    int total = 0;
    for (int i = 0; i < n; i++) {
      total += sortedScores[i];
    }
    System.out.println("total " + total);
    System.out.println("中位数：" + sortedScores[(n - 1) / 2] + "条件：" + a);
    System.out.println("算术平均值：" + total / n + "条件：" + b);
    System.out.println("去极值算术平均值：" + (total - min - max) / (n - 2)  + "条件：" + c);
    return sortedScores[(n - 1) / 2] >= a || total / n >= b || (total - min - max) / (n - 2) >= c;
  }
}
