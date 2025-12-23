package org.example.alvin.algorithm.leetcode.linkedlist;

public class LCP25 {
  public static void main(String[] args) {

  }

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode hair = new ListNode(0);
    hair.next = head;
    ListNode pre = hair;

    while (head != null) {
      ListNode tail = pre;
      for (int i = 0; i < k; i++) {
        tail = tail.next;
        if (tail == null) {
          return hair.next;
        }
      }
      ListNode newSubHead = tail.next;
      ListNode[] reverse = reverseHelper(head, tail);
      head = reverse[0];
      tail = reverse[1];
      pre.next = head;
      tail.next = newSubHead;
      pre = tail;
      head = newSubHead;
    }
    return hair.next;
  }

  private ListNode[] reverseHelper(ListNode head, ListNode tail) {
    ListNode pre = null;
    ListNode curr = head;
    // 使用 pre != tail 则每一个分组之间不需要断开next链接
    while (pre != tail) {
      ListNode tmp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = tmp;
    }
    return new ListNode[]{pre, head};
  }
}
