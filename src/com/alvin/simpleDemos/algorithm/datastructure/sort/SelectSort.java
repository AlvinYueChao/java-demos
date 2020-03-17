package com.alvin.simpleDemos.algorithm.datastructure.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] inputList = {1, -2, 10, 20, -5, 12, 10};
        selectSort(inputList);
        System.out.println(Arrays.toString(inputList));
    }

    private static void selectSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                minIndex = list[i] < list[j] ? i : j;
            }

            if (minIndex != i) {
                int temp = list[i];
                list[i] = list[minIndex];
                list[minIndex] = temp;
            }
        }
    }
}
