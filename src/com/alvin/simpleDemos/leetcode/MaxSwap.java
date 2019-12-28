package com.alvin.simpleDemos.leetcode;

import java.util.Arrays;

/**
 * LeetCode 670
 */
public class MaxSwap {
    public static void main(String[] args) {
        MaxSwap instance = new MaxSwap();
        int input = 2736;
        System.out.println("Input: " + input);
        System.out.println("Output: " + instance.maximumSwap(input));
    }

    public int maximumSwap(int num) {
        String numStr = String.valueOf(num);
        int[] numList = new int[numStr.length()];

        int[] lastLocation = new int[10];
        Arrays.fill(lastLocation, -1);

        boolean flag = false;

        for (int i = numList.length - 1; i >= 0; i--) {
            numList[i] = Integer.parseInt(String.valueOf(numStr.charAt(i)));
            if (lastLocation[numList[i]] == -1) {
                lastLocation[numList[i]] = i;
            }
        }

        for (int i = 0; i < numList.length; i++) {
            for (int j = 9; j > numList[i]; j--) {
                if (lastLocation[j] != -1 && lastLocation[j] > i) {
                    flag = true;
                    int temp = numList[i];
                    numList[i] = j;
                    numList[lastLocation[j]] = temp;
                }

                if (flag) break;
            }
            if (flag) break;
        }
        return transfer(numList);
    }

    public int transfer(int[] arr) {
        StringBuilder result = new StringBuilder();
        for (int item : arr) {
            result.append(item);
        }
        return Integer.parseInt(result.toString());
    }
}