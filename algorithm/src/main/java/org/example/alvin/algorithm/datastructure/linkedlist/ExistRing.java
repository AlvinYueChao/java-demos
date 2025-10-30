package org.example.alvin.algorithm.datastructure.linkedlist;

public class ExistRing {
  public static void main(String[] args) {
    ListNode<String> first = new ListNode<>("aa");
    ListNode<String> second = new ListNode<>("bb");
    ListNode<String> third = new ListNode<>("cc");
    ListNode<String> fourth = new ListNode<>("dd");
    ListNode<String> fifth = new ListNode<>("ee");
    first.next = second;
    second.next = third;
    third.next = fourth;
    fourth.next = fifth;
    fifth.next = first;

    System.out.println("是否存在环: " + existRing(first));
    System.out.println(
        "环的入口: " + (getEntranceOfRing(first) == null ? "null" : getEntranceOfRing(first).val));
  }

  private static boolean existRing(ListNode<?> head) {
    if (head == null) {
      return false;
    }

    ListNode<?> slow = head;
    ListNode<?> fast = head;

    boolean existRing = false;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (fast == slow) {
        existRing = true;
        break;
      }
    }
    return existRing;
  }

  private static ListNode<?> getEntranceOfRing(ListNode<?> head) {
    if (head == null) {
      return null;
    }

    ListNode<?> slow = head;
    ListNode<?> fast = head;

    boolean existRing = false;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (fast == slow) {
        existRing = true;
        break;
      }
    }
    return existRing ? slow : null;
  }
}
