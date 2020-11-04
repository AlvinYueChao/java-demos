package org.example.alvin.algorithm.datastructure.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] nums = {10, -3, 2, 0, 1, 7, 2, -5};
        shellSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void shellSort(int[] nums) {
        // 希尔排序，分组依据 groupCount 确定规则
        // 提高插入排序的原理：每次不止插入一个元素，而是多个元素
        int groupCount = 1;
        while (groupCount < nums.length / 2) {
            groupCount = groupCount * 2 + 1;
        }

        for (int i = groupCount; i >= 1; i = i / 2) {
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j] > nums[j + i]) {
                    int temp = nums[j];
                    nums[j] = nums[j + i];
                    nums[j + i] = temp;
                }
            }
        }
    }
}
