package org.example.alvin.algorithm.leetcode.linkedlist;

public class LCP92 {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5};
    ListNode head = ListNode.convertAndGetHead(arr);
    LCP92 lcp92 = new LCP92();
    ListNode.printAsArray(lcp92.reverseBetween(head, 2, 4));
  }

  public ListNode reverseBetween(ListNode head, int left, int right) {
    // 使用虚拟头节点，避免复杂分类讨论
    ListNode dummyHead = new ListNode(-501);
    dummyHead.next = head;
    // 从虚拟头节点出发，找到left-1对应的节点preNode
    ListNode preNode = dummyHead;
    for (int i = 0; i < left - 1; i++) {
      preNode = preNode.next;
    }
    // 找到left和right对应的leftNode和rightNode，找到right+1对应的节点currNode
    ListNode leftNode = preNode.next;
    ListNode rightNode = leftNode;
    for (int i = 0; i < right - left; i++) {
      rightNode = rightNode.next;
    }
    ListNode currNode = rightNode.next;
    // 截取子链表，切断前后指针，反转子链表
    preNode.next = null;
    rightNode.next = null;
    reverse(leftNode);
    // 拼接新链表，返回新链表头节点
    preNode.next = rightNode;
    leftNode.next = currNode;
    return dummyHead.next;
  }

  private void reverse(ListNode head) {
    ListNode pre = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }
  }

  private ListNode complexReverseBetweenImpl(ListNode head, int left, int right) {
    int n = 1;
    ListNode curr = head;
    while (curr != null) {
      curr = curr.next;
      n++;
    }

    if (left == 1 && right == n) {
      int index = left;
      ListNode tailToHead = head;
      while (index < right) {
        tailToHead = tailToHead.next;
        index++;
      }
      reverseInPlace(head, tailToHead);
      return tailToHead;
    } else if (n > 1 && left == 1 && right < n) {
      int index = left;
      ListNode tailToHead = head;
      while (index < right) {
        tailToHead = tailToHead.next;
        index++;
      }
      ListNode right1Node = tailToHead.next;
      reverseInPlace(head, tailToHead);
      head.next = right1Node;
      return tailToHead;
    } else if (n > 1 && left > 1 && right == n) {
      int index = 1;
      ListNode left1Node = null;
      ListNode headToTail = head;
      while (index < left) {
        left1Node = headToTail;
        headToTail = headToTail.next;
        index++;
      }
      ListNode tailToHead = headToTail;
      while (index < right) {
        tailToHead = tailToHead.next;
        index++;
      }
      reverseInPlace(headToTail, tailToHead);
      left1Node.next = tailToHead;
      return head;
    } else {
      int index = 1;
      ListNode left1Node = null;
      ListNode headToTail = head;
      while (index < left) {
        left1Node = headToTail;
        headToTail = headToTail.next;
        index++;
      }
      ListNode tailToHead = headToTail;
      while (index < right) {
        tailToHead = tailToHead.next;
        index++;
      }
      ListNode right1Node = tailToHead.next;
      reverseInPlace(headToTail, tailToHead);
      left1Node.next = tailToHead;
      headToTail.next = right1Node;
      return head;
    }
  }

  private void reverseInPlace(ListNode headToTail, ListNode tailToHead) {
    if (headToTail == tailToHead) {
      return;
    }
    ListNode pre = null;
    ListNode curr = headToTail;
    while (pre != tailToHead) {
      ListNode temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }
  }
}
