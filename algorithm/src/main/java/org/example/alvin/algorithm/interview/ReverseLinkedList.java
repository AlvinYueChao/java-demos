package org.example.alvin.algorithm.interview;

import org.example.alvin.algorithm.leetcode.linkedlist.ListNode;

/**
 * 目标是将 L0 -> L1 -> ... -> Ln-1 -> Ln 变成 L0 -> Ln -> L1 -> Ln-1 -> ...
 *
 * <p>以 1->2->3->4->5->6 为例，目标是 1->6->2->5->3->4
 */
public class ReverseLinkedList {
  public static void main(String[] args) {
    //    int[] nums = {1, 2, 3, 4, 5, 6};
    int[] nums = {1, 2, 3, 4, 5};
    ListNode head = ListNode.convertAndGetHead(nums);
    reverseLinkedList(head);
    System.out.println(head);
  }

  public static void reverseLinkedList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    int length = 0;
    ListNode start = head;
    while (start != null) {
      length++;
      start = start.next;
    }
    int mid = length % 2 == 0 ? length / 2 : length / 2 + 1;
    ListNode midNode = head;
    for (int i = 0; i < mid; i++) {
      midNode = midNode.next;
    }

    // split to two linkedList
    ListNode midNodeNext = midNode.next;
    midNode.next = null;

    // reverse mid to end
    ListNode pre = null;
    ListNode cur = midNodeNext;
    while (cur != null) {
      ListNode tmp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = tmp;
    }

    ListNode left = head;
    ListNode right = pre;
    while (right != null) {
      ListNode tmp = left.next;
      ListNode tmp2 = right.next;
      left.next = right;
      right.next = tmp;
      left = tmp;
      right = tmp2;
    }
  }
}
