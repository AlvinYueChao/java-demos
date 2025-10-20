package org.example.alvin.algorithm.leetcode.linkedlist;

public class LCP82 {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 3, 4, 4, 5};
    ListNode head = ListNode.convertAndGetHead(arr);
    ListNode.printAsArray(new LCP82().deleteDuplicates(head));
  }

  /**
   * 自定义实现，同样执行时间<1ms，可读性高
   * @param head
   * @return
   */
  public ListNode deleteDuplicates(ListNode head) {
    ListNode dummyHead = new ListNode(-101);
    dummyHead.next = head;
    ListNode pre = dummyHead; //记录当前待识别节点的上一个节点
    ListNode subStart = dummyHead.next; //记录当前待识别节点
    while (subStart != null) {
      ListNode curr = subStart.next;  //记录当前待识别节点的下一个节点
      if (curr != null && curr.val == subStart.val) {
        while (curr != null && curr.val == subStart.val) {
          curr = curr.next;
        }
        pre.next = curr;  //截取重复数字的节点
        subStart = curr;  //从当前不重复数字开始重新识别
      } else {
        // pre和subStart都往后移动一位
        pre = pre.next;
        subStart = subStart.next;
      }
    }
    return dummyHead.next;
  }

  /**
   * leetcode提交中执行速度 < 1ms 的题解，代码行数少，但可读性差
   *
   * @param head
   * @return
   */
  public ListNode deleteDuplicates1(ListNode head) {
    ListNode dummy = new ListNode(0, head);
    ListNode cur = dummy;
    while (cur.next != null && cur.next.next != null) {
      int val = cur.next.val;
      if (cur.next.next.val == val) { // 后两个节点值相同
        // 值等于 val 的节点全部删除
        while (cur.next != null && cur.next.val == val) {
          cur.next = cur.next.next;
        }
      } else {
        cur = cur.next;
      }
    }
    return dummy.next;
  }
}
