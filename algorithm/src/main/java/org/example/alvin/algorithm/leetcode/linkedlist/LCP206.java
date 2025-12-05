package org.example.alvin.algorithm.leetcode.linkedlist;

import java.util.Arrays;

public class LCP206 {
  public ListNode reverseList(ListNode head) {
    ListNode cur = head;
    ListNode pre = null;
    int len = 0;
    while (cur != null) {
      len++;
      ListNode tmp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = tmp;
    }
    ListNode tmp = pre;
    int[] result = new int[len];
    for (int i = 0; i < len && tmp != null; i++) {
      result[i] = tmp.val;
      tmp = tmp.next;
    }
    System.out.println(Arrays.toString(result));
    return pre;
  }

  public static void main(String[] args) {
    LCP206 test = new LCP206();
    ListNode head = ListNode.convertAndGetHead(new int[] {1, 2, 3, 4, 5});
    ListNode result = test.reverseList(head);
  }
}
