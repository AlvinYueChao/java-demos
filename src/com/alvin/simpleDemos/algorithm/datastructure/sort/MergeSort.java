package com.alvin.simpleDemos.algorithm.datastructure.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] temp = new int[nums.length];
        sort(nums, 0, nums.length - 1, temp);
        System.out.println(Arrays.toString(nums));
    }

    private static void sort(int[] nums, int left, int right, int[] temp) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort(nums, left, middle, temp);
            sort(nums, middle + 1, right, temp);
            merge(nums, left, middle, right, temp);
        }
    }

    private static void merge(int[] nums, int left, int middle, int right, int[] temp) {
        int i = left;
        int j = middle + 1;
        int t = 0;
        while (i <= middle && j <= right) {
            if (nums[i] < nums[j]) {
                temp[t++] = nums[i++];
            }
            else {
                temp[t++] = nums[j++];
            }
        }
        while (i <= middle) {
            temp[t++] = nums[i++];
        }
        while (j <= right) {
            temp[t++] = nums[j++];
        }

        t = 0;
        while (left <= right) {
            nums[left++] = temp[t++];
        }
    }
}
