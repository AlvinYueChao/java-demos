package org.example.alvin.algorithm.leetcode;

import java.util.Arrays;

public class LCP88 {
  public static void main(String[] args) {
    /*int[] nums1 = {1, 2, 3, 0, 0, 0};
    int m = 3;
    int[] nums2 = {2, 5, 6};
    int n = 3;*/
    int[] nums1 = {1};
    int m = 1;
    int[] nums2 = {};
    int n = 0;
    merge(nums1, m, nums2, n);
    System.out.println(Arrays.toString(nums1));
  }

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int k = m + n;
    int[] temp = new int[k];
    for (int i = 0, nums1Idx = 0, nums2Idx = 0; i < k; i++) {
      if (nums1Idx >= m) {
        temp[i] = nums2[nums2Idx++];
      } else if (nums2Idx >= n) {
        temp[i] = nums1[nums1Idx++];
      } else {
        temp[i] = nums1[nums1Idx] < nums2[nums2Idx] ? nums1[nums1Idx++] : nums2[nums2Idx++];
      }
    }
    for (int i = 0; i < k; i++) {
      nums1[i] = temp[i];
    }
  }
}
