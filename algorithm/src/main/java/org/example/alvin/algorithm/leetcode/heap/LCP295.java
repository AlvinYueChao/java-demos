package org.example.alvin.algorithm.leetcode.heap;

import java.util.PriorityQueue;

public class LCP295 {

  static class MedianFinder {
    // 小根堆，用于从大于中位数的堆中快速找到最小的值
    PriorityQueue<Integer> queueMax;
    // 大根堆，用于从小于等于中位数的堆中快速找到最大的值
    PriorityQueue<Integer> queueMin;

    public MedianFinder() {
      // 默认从小到大，小根堆
      queueMax = new PriorityQueue<>((a, b) -> (a - b));
      // 倒序，大根堆
      queueMin = new PriorityQueue<>((a, b) -> (b - a));
    }

    public void addNum(int num) {
      if (queueMin.isEmpty() || num <= queueMin.peek()) {
        queueMin.offer(num);
        if (queueMax.size() + 1 < queueMin.size()) {
          queueMax.offer(queueMin.poll());
        }
      } else {
        queueMax.offer(num);
        if (queueMax.size() > queueMin.size()) {
          queueMin.offer(queueMax.poll());
        }
      }
    }

    public double findMedian() {
      if (queueMin.size() > queueMax.size()) {
        // queueMin.size() == queueMax.size() + 1
        return queueMin.peek();
      } else {
        // queueMin.size() == queueMax.size()
        return (queueMax.peek() + queueMin.peek()) / 2.0d;
      }
    }
  }

  public static void main(String[] args) {
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);
    medianFinder.addNum(2);
    System.out.println(medianFinder.findMedian());
    medianFinder.addNum(3);
    System.out.println(medianFinder.findMedian());
  }
}
