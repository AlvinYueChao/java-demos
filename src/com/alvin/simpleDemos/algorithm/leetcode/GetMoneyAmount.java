package com.alvin.simpleDemos.algorithm.leetcode;

/**
 * leetcode 375
 */
public class GetMoneyAmount {
    public static void main(String[] args) {
        int inputN = 10;
        int result = getMaxAmount(1, inputN);
    }

    private static int getMaxAmount(int head, int tail) {
        if (head >= tail) {
            return 0;
        }

        int minRes = Integer.MAX_VALUE;
        for (int i = (head + tail) / 2; i <= tail ; i++) {
            int res = i + Math.max(getMaxAmount(head, i - 1), getMaxAmount(i + 1, tail));
            minRes = Math.min(res, minRes);
        }
        return minRes;
    }
}
