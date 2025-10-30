package org.example.alvin.algorithm.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
  public int val;
  public ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public static ListNode convertAndGetHead(int[] arr) {
    if (arr == null || arr.length == 0) {
      return null;
    }

    ListNode head = new ListNode(arr[arr.length - 1]);
    for (int i = arr.length - 2; i >= 0; i--) {
      head = new ListNode(arr[i], head);
    }

    return head;
  }

  public static void printAsArray(ListNode head) {
      ListNode curr = head;
      List<Integer> list = new ArrayList<>();
      while (curr != null) {
        list.add(curr.val);
        curr = curr.next;
      }
      System.out.println(list);
  }

  @Override
  public String toString() {
    return "ListNode{" +
      "val=" + val +
      ", next=" + next +
      '}';
  }
}
