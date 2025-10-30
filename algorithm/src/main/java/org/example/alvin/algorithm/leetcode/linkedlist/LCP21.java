package org.example.alvin.algorithm.leetcode.linkedlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LCP21 {
  public static void main(String[] args) {
    int[] l1 = {1, 2, 4};
    ListNode ln1 = convertArraysToListNode(l1);
    log.info(ln1.toString());
  }

  public static ListNode convertArraysToListNode(int[] arr) {
    ListNode head = new ListNode(0);
    ListNode p = head;
    for (int i = 0; i < arr.length; i++) {
      ListNode newNode = new ListNode(arr[i]);
      p.next = newNode;
      p = p.next;
    }
    return head.next;
  }
}
