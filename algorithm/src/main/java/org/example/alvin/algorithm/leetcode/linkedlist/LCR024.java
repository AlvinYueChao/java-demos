package org.example.alvin.algorithm.leetcode.linkedlist;

public class LCR024 {
  public ListNode reverseList(ListNode head) {
    ListNode cur = head;
    ListNode pre = null;
    while (cur != null) {
      ListNode tmp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = tmp;
    }
    return pre;
  }

  public static void main(String[] args) {
    LCR024 test = new LCR024();
    int[] arr = {1, 2, 3, 4, 5};
    ListNode head = ListNode.convertAndGetHead(arr);
    ListNode last = test.reverseList(head);
    System.out.println(last);
  }
}
