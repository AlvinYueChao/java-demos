package org.example.alvin.algorithm;

import java.util.*;

public class Demos {
  public static void main(String[] args) {
    Set<List<Integer>> res = new HashSet<>();
    res.add(Arrays.asList(1, 2, 3));
    System.out.println(res.contains(Arrays.asList(1, 2, 3)));
    res.add(Arrays.asList(1, 2, 3));
    System.out.println(res.size());
  }

  private static void stack2Deque() {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);

    Deque<Integer> stack1 = new ArrayDeque<>();
    stack1.offer(1);
    stack1.offer(2);
    stack1.offer(3);

    Integer top = stack.pop();
    Integer top1 = stack1.pollLast();
    System.out.println(top);
    System.out.println(top.equals(top1));
  }

  private static void arraySort() {
    List<int[]> list = Arrays.asList(new int[] {1, 2}, new int[] {3, 4});
    System.out.println(list);

    // 修正版本 - 将二维数组的每个一维数组作为独立元素传入
    List<int[]> list1 = Arrays.asList(new int[] {1, 2}, new int[] {3, 4}, new int[] {2, 3});
    list1.sort((a, b) -> a[0] - b[0]);
    // 或者将 List 转换为数组再排序
    //     int[][] array = list1.toArray(new int[0][]);
    //     Arrays.sort(array, (a, b) -> a[0] - b[0]);
  }

  private static void printFormatedArrays() {
    int[] nums = {1, 2, 3, 4, 5};
    System.out.println(Arrays.toString(nums));

    int[][] arr = new int[3][3];
    System.out.println(Arrays.deepToString(arr));
  }

  private static void charCalcTest() {
    //    int a1 = '2' - '0' - 1;
    //    int a2 = (int) '2' - '1';
    //    System.out.println(a1 == a2);
    int a1 = 'm' - 'a';
    int a2 = 'd' - 'a';
    System.out.println(a1);
    System.out.println(a2);
  }

  private static void logicalBitMovingTest() {
    int a = 10 >>> 1;
    System.out.println(a);
  }
}
