package org.example.alvin.algorithm.leetcode.dynamicplanning;

public class LCP123 {
  public static void main(String[] args) {

  }

  public int maxProfit(int[] prices) {
    /**
     * 定义四个状态变量
     * buy1: 第一次买入后的最大余额（负数）
     * sell1: 第一次卖出后的最大利润
     * buy2: 第二次买入后的最大余额（基于第一次的利润减去当前股价）
     * sell2: 第二次卖出后的最大利润
     */
    int buy1 = -prices[0];
    int sell1 = 0;
    int buy2 = -prices[0];
    int sell2 = 0;

    for (int i = 1; i < prices.length; i++) {
      /**
       * 注意：这里是有依赖关系的，但在同一天内，顺序其实不影响最终结果
       * 因为如果同一天买卖，利润为0，状态会自动平衡
       */
      buy1 = Math.max(buy1, -prices[i]);
      sell1 = Math.max(sell1, buy1 + prices[i]);
      buy2 = Math.max(buy2, sell1 - prices[i]);
      sell2 = Math.max(sell2, buy2 + prices[i]);
    }

    return sell2;
  }
}
