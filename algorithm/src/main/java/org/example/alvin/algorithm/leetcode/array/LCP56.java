package org.example.alvin.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCP56 {
  public static void main(String[] args) {
    int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    System.out.println(Arrays.deepToString(new LCP56().merge(intervals)));
  }

  public int[][] merge(int[][] intervals) {
    if (intervals.length == 1) {
      return intervals;
    } else {
      int rows = intervals.length;
      int columns = 2;
      sort(intervals, rows, columns);
      List<List<Integer>> tempAns = new ArrayList<>();
      for (int i = 0; i < rows; i++) {
        if (tempAns.isEmpty() || tempAns.getLast().get(1) < intervals[i][0]) {
          // 当前区间的起点大于结果中最后一个区间的终点，则当前区间应该被加入结果中
          tempAns.add(Arrays.asList(intervals[i][0], intervals[i][1]));
        } else {
          // 当前区间的终点大于结果中最后一个区间的终点，则有部分重合，更新结果中最后一个区间的终点
          if (tempAns.getLast().get(1) < intervals[i][1]) {
            tempAns.getLast().set(1, intervals[i][1]);
          }
          // 当前区间的终点小于等于结果中最后一个区间的终点，则完全重合，不需要任何操作
        }
      }
      int[][] ans = new int[tempAns.size()][2];
      for (int i = 0; i < tempAns.size(); i++) {
        List<Integer> currentInterval = tempAns.get(i);
        ans[i] = new int[] {currentInterval.get(0), currentInterval.get(1)};
      }
      return ans;
    }
  }

  // 归并排序
  private void sort(int[][] intervals, int rows, int columns) {
    int[][] temp = new int[rows][columns];
    mergeSort(intervals, 0, rows - 1, temp);
  }

  private void mergeSort(int[][] arr, int left, int right, int[][] temp) {
    if (left < right) {
      int mid = (left + right) / 2;
      mergeSort(arr, left, mid, temp); // 递归排序左半部分
      mergeSort(arr, mid + 1, right, temp); // 递归排序右半部分
      merge(arr, left, mid, right, temp); // 合并两个有序部分
    }
  }

  private void merge(int[][] arr, int left, int mid, int right, int[][] temp) {
    int i = left; // 左序列指针
    int j = mid + 1; // 右序列指针
    int t = 0; // 临时数组指针

    // 1. 将左右两边的有序序列按顺序填充到 temp 数组中
    while (i <= mid && j <= right) {
      // 根据区间的起点升序排序各区间
      if (arr[i][0] <= arr[j][0]) {
        temp[t++] = arr[i++];
      } else {
        temp[t++] = arr[j++];
      }
    }

    // 2. 将剩余的元素（如果有）拷贝到 temp 数组
    while (i <= mid) {
      temp[t++] = arr[i++];
    }
    while (j <= right) {
      temp[t++] = arr[j++];
    }

    // 3. 将 temp 数组的元素拷贝回原数组的对应位置
    t = 0;
    int tempLeft = left;
    while (tempLeft <= right) {
      arr[tempLeft++] = temp[t++];
    }
  }
}
