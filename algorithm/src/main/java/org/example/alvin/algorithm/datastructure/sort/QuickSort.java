package org.example.alvin.algorithm.datastructure.sort;

import java.util.Arrays;

public class QuickSort {
  public static void main(String[] args) {
    int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    quickSort(nums, 0, nums.length - 1);
    System.out.println(Arrays.toString(nums));
  }

  private static void quickSort(int[] arr, int low, int high) {
    if (low > high) {
      return;
    }
    int i = low;
    int j = high;
    int pivot = arr[low];

    while (i < j) {
      // 从哪个方向选基准点，则遍历时先便利反方向
      while (pivot <= arr[j] && i < j) {
        j--;
      }
      while (pivot >= arr[i] && i < j) {
        i++;
      }
      if (i < j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    arr[low] = arr[i];
    arr[i] = pivot;

    // 递归调用左半数组
    quickSort(arr, low, j - 1);
    // 递归调用右半数组
    quickSort(arr, j + 1, high);
  }
}
