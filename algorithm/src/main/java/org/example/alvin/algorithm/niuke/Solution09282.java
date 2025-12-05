package org.example.alvin.algorithm.niuke;

import java.util.Scanner;

/**
 * 用例通过率 80% 贪心的商人 时间限制：1秒 空间限制：262MB 描述：
 * 商人经营一家店铺，有number中商品，由于仓库限制每件商品的最大持有数量是item[index]，每种商品的价格在每天是item_price[item_index][day]
 * 通过对商品的买进和卖出获取利润，请给出商人在days天内能获取到的最大的利润 注：同一件商品可以反复买进和卖出 输入描述： 3 // 输入的商品的数量numbers 3 // 输入商品售货天数
 * days 4 5 6 // 输入仓库限制每件商品的最大持有数量是item[index] 1 2 3 // 输入第一件商品每天的价格 4 5 6 // 输入第二件商品每天的价格 1 5 3 //
 * 输入第三件商品每天的价格 输出描述： 32 // 输出商人在这段时间内的最大利润 补充说明： 根据输入的信息： numbers=3 days=3 item[3]={4, 5, 6}
 * item_price[3][4]={{1, 2, 3}, {4, 3, 2}, {1, 5, 3}}
 * 针对第一件商品，商人在第一天的价格是item_price[0][0]=1时买入item[0]件，在第二天item_price[0][2]=3 的时候卖出，获利最大是8；
 * 针对第二件商品，不进行交易，获利最大是0
 * 针对第三件商品，商人在第一天的价格是item_price[2][0]=1时买入item[2]件，在第二天item_price[2][1]=5的时候卖出，获利最大是24；
 * 因此这段时间商人能获取的最大利润是 8+24=32 示例1： 输入： 3 3 4 5 6 1 2 3 4 3 2 1 5 3 输出： 32 示例2： 输入： 1 1 1 1 输出： 0
 */
public class Solution09282 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numbers = scanner.nextInt();
    int days = scanner.nextInt();
    int[] item = new int[numbers];
    for (int i = 0; i < numbers; i++) {
      item[i] = scanner.nextInt();
    }

    // 读取每种商品每天的价格
    int[][] itemPrice = new int[numbers][days];
    for (int i = 0; i < numbers; i++) {
      for (int j = 0; j < days; j++) {
        itemPrice[i][j] = scanner.nextInt();
      }
    }

    scanner.close();

    long maxProfit = calculateMaxProfit(numbers, days, item, itemPrice);
    System.out.println(maxProfit);
  }

  public static long calculateMaxProfit(int numbers, int days, int[] item, int[][] itemPrice) {
    long totalProfit = 0;

    // 对每种商品分别计算最大利润
    for (int i = 0; i < numbers; i++) {
      int maxHold = item[i]; // 当前商品最大持有数量
      int[] prices = itemPrice[i]; // 当前商品每天的价格
      long productProfit = 0;

      // 贪心策略：只要明天价格比今天高，就今天买入明天卖出
      for (int day = 0; day < days - 1; day++) {
        if (prices[day + 1] > prices[day]) {
          // 明天价格更高，今天买入明天卖出
          productProfit += (long) (prices[day + 1] - prices[day]) * maxHold;
        }
      }

      totalProfit += productProfit;
    }

    return totalProfit;
  }
}
