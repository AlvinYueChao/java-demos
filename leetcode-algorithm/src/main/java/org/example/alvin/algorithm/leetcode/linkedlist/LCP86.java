package org.example.alvin.algorithm.leetcode.linkedlist;

public class LCP86 {
  public static void main(String[] args) {
    int[] arr = {1, 4, 3, 2, 5, 2};
    ListNode head = ListNode.convertAndGetHead(arr);
    ListNode result = new LCP86().partition(head, 3);
    ListNode.printAsArray(result);
  }

  public ListNode partition(ListNode head, int x) {
    ListNode small = new ListNode(0);
    ListNode smallHead = small;
    ListNode large = new ListNode(0);
    ListNode largeHead = large;
    while (head != null) {
      if (head.val < x) {
        small.next = head;
        small = small.next;
      } else {
        large.next = head;
        large = large.next;
      }
      head = head.next;
    }
    large.next = null;
    small.next = largeHead.next;
    return smallHead.next;
  }
}
