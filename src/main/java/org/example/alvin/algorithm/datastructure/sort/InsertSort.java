package org.example.alvin.algorithm.datastructure.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] inputList = {1, -2, 10, 20, -5, 12, 10};
        insertSort(inputList);
        System.out.println(Arrays.toString(inputList));
    }

    private static void insertSort(int[] numbers) {
        // 每次获取一个数，不断与左侧相邻数比较，将较小的数向左移到正确位置
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j > 0; j--) {
                if (numbers[j - 1] > numbers[j]) {
                    int temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                }
                else {
                    break;
                }
            }
        }
    }
}
