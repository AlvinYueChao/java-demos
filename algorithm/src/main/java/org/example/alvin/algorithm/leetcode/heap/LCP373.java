package org.example.alvin.algorithm.leetcode.heap;

import java.util.*;

public class LCP373 {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> ans = new ArrayList<>();

    Queue<int[]> queue =
        new PriorityQueue<>(Comparator.comparingInt(a -> nums1[a[0]] + nums2[a[1]]));
    for (int i = 0; i < nums1.length; i++) {
      // 将每一行的第一个元素放入队列中
      queue.offer(new int[] {i, 0});
    }
    while (k > 0 && !queue.isEmpty()) {
      int[] minPair = queue.poll();
      ans.add(List.of(nums1[minPair[0]], nums2[minPair[1]]));
      if (minPair[1] + 1 < nums2.length) {
        queue.offer(new int[] {minPair[0], minPair[1] + 1});
      }
      k--;
    }

    return ans;
  }

  public static void main(String[] args) {
    LCP373 lcp373 = new LCP373();
    int[] nums1 = {1, 7, 11};
    int[] nums2 = {2, 4, 6};
    int k = 3;
    System.out.println(lcp373.kSmallestPairs(nums1, nums2, k));
  }
}
