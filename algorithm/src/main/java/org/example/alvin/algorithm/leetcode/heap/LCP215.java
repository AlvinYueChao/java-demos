package org.example.alvin.algorithm.leetcode.heap;

public class LCP215 {
  public int findKthLargest(int[] nums, int k) {
    int heapSize = nums.length;
    buildMaxHeap(nums, heapSize);
    for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
      swap(nums, 0, i);
      heapSize--;
      shiftDown(nums, 0, heapSize);
    }
    return nums[0];
  }

  // 将前heapSize个元素原地构建大顶堆
  private void buildMaxHeap(int[] nums, int heapSize) {
    for (int i = heapSize / 2 - 1; i >= 0; i--) {
      shiftDown(nums, i, heapSize);
    }
  }

  // 交换两个指定两个位置的元素
  private void swap(int[] nums, int currentIndex, int candidateIndex) {
    int temp = nums[currentIndex];
    nums[currentIndex] = nums[candidateIndex];
    nums[candidateIndex] = temp;
  }

  // 将指定位置的元素下沉
  private void shiftDown(int[] nums, int index, int heapSize) {
    while (index * 2 + 1 < heapSize) {
      int leftChild = index * 2 + 1;
      int rightChild = index * 2 + 2;

      int largestChildIndex = leftChild;

      if (rightChild < heapSize && nums[rightChild] > nums[leftChild]) {
        largestChildIndex = rightChild;
      }

      if (nums[index] >= nums[largestChildIndex]) {
        break;
      }

      swap(nums, index, largestChildIndex);
      index = largestChildIndex;
    }
  }
}
